package org.example.academic.system;

import org.example.academic.system.exception.AcademicClassNotFoundException;
import org.example.academic.system.exception.InvalidAssessmentTypeException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.AssessmentType;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.validation.DomainValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes do AssessmentService (TUS-2402). */
class AssessmentServiceTest {

    private final AcademicSystem academicSystem = AcademicSystem.getInstance();
    private AssessmentService service;
    private AcademicClass academicClass;

    @BeforeEach
    void setUp() {
        academicSystem.reset();
        service = new AssessmentService(academicSystem, new DomainValidator());
        academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicSystem.addClass(academicClass);
    }

    @Test
    void registersAssessmentInExistingClass() {
        service.registerAssessment("CC101", "EXAM", "Prova 1", 10.0, 0.4);
        assertEquals(1, academicClass.getAssessments().size());
        assertEquals(AssessmentType.EXAM, academicClass.getAssessments().get(0).getType());
    }

    @Test
    void invalidAssessmentTypeDoesNotAddAssessment() {
        assertThrows(InvalidAssessmentTypeException.class,
                () -> service.registerAssessment("CC101", "BANANA", "Prova 1", 10.0, 0.4));
        assertTrue(academicClass.getAssessments().isEmpty());
    }

    @Test
    void nonexistentClassCodeDoesNotAddAssessment() {
        assertThrows(AcademicClassNotFoundException.class,
                () -> service.registerAssessment("ZZ999", "EXAM", "Prova 1", 10.0, 0.4));
        assertTrue(academicClass.getAssessments().isEmpty());
    }
}
