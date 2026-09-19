package org.example.academic.system.service;

import org.example.academic.system.AcademicSystem;
import org.example.academic.system.exception.AcademicClassNotFoundException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.InvalidAssessmentTypeException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.AssessmentType;
import org.example.academic.system.model.Assignment;
import org.example.academic.system.model.Exam;
import org.example.academic.system.model.PracticalAssignment;
import org.example.academic.system.model.Seminar;
import org.example.academic.system.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Regras de negocio relacionadas a avaliacoes (TUS-2397). Localiza a
 * turma alvo, cria o tipo correto de avaliacao (Factory Method via
 * switch sobre o enum), valida e adiciona a turma.
 */
public class AssessmentService {

    private static final Logger AUDIT = LoggerFactory.getLogger("AUDIT");

    private final AcademicSystem academicSystem;
    private final DomainValidator validator;

    public AssessmentService(AcademicSystem academicSystem, DomainValidator validator) {
        this.academicSystem = academicSystem;
        this.validator = validator;
    }

    public Assessment registerAssessment(String classCode, String typeName,
                                         String description, Double value, Double weight) {
        try {
            AcademicClass target = academicSystem.findClassByCode(classCode)
                    .orElseThrow(() -> new AcademicClassNotFoundException(classCode));
            Assessment assessment = createAssessment(typeName, description, value, weight);
            validator.validate(assessment);
            target.addAssessment(assessment);
            AUDIT.info("Avaliacao cadastrada na turma [{}]: tipo [{}], descricao [{}]",
                    classCode, assessment.getType(), assessment.getDescription());
            return assessment;
        } catch (AcademicSystemException e) {
            AUDIT.warn("Falha no cadastro de avaliacao: {}", e.getMessage());
            throw e;
        }
    }

    private Assessment createAssessment(String typeName, String description,
                                        Double value, Double weight) {
        AssessmentType type = parseType(typeName);
        return switch (type) {
            case EXAM -> new Exam(description, value, weight);
            case PRACTICAL_ASSIGNMENT -> new PracticalAssignment(description, value, weight);
            case SEMINAR -> new Seminar(description, value, weight);
            case ASSIGNMENT -> new Assignment(description, value, weight);
        };
    }

    private AssessmentType parseType(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            throw new InvalidAssessmentTypeException(String.valueOf(typeName));
        }
        String normalized = typeName.trim().toUpperCase().replace(' ', '_');
        for (AssessmentType type : AssessmentType.values()) {
            if (type.name().equals(normalized)
                    || type.getLabel().equalsIgnoreCase(typeName.trim())) {
                return type;
            }
        }
        throw new InvalidAssessmentTypeException(typeName);
    }
}
