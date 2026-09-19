package org.example.academic.system.service;

import org.example.academic.system.AcademicSystem;
import org.example.academic.system.exception.AcademicClassNotFoundException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.DuplicateClassException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Regras de negocio relacionadas a turmas (TUS-2396). Mantem o
 * controller livre de logica de negocio (GRASP: Controller + High Cohesion).
 */
public class ClassService {

    private static final Logger AUDIT = LoggerFactory.getLogger("AUDIT");

    private final AcademicSystem academicSystem;
    private final DomainValidator validator;

    public ClassService(AcademicSystem academicSystem, DomainValidator validator) {
        this.academicSystem = academicSystem;
        this.validator = validator;
    }

    public AcademicClass registerClass(String code, String title) {
        try {
            AcademicClass academicClass = new AcademicClass(
                    code == null ? null : code.trim(),
                    title == null ? null : title.trim());
            validator.validate(academicClass);
            if (academicSystem.findClassByCode(academicClass.getCode()).isPresent()) {
                throw new DuplicateClassException(academicClass.getCode());
            }
            academicSystem.addClass(academicClass);
            AUDIT.info("Turma cadastrada: [{}] - {}", academicClass.getCode(), academicClass.getTitle());
            return academicClass;
        } catch (AcademicSystemException e) {
            AUDIT.warn("Falha no cadastro de turma: {}", e.getMessage());
            throw e;
        }
    }

    public List<AcademicClass> listClasses() {
        return academicSystem.getClasses();
    }

    public AcademicClass findByCode(String code) {
        return academicSystem.findClassByCode(code)
                .orElseThrow(() -> new AcademicClassNotFoundException(code));
    }
}
