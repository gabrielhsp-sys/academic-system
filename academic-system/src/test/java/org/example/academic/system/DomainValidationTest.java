package org.example.academic.system;

import org.example.academic.system.exception.DomainValidationException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.validation.DomainValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Testes de validacao do dominio academico (TUS-2385). */
class DomainValidationTest {

    private final DomainValidator validator = new DomainValidator();

    @Test
    void validClassPassesValidation() {
        assertDoesNotThrow(() ->
                validator.validate(new AcademicClass("CC101", "Orientacao a Objetos")));
    }

    @Test
    void classWithBlankCodeFailsValidation() {
        assertThrows(DomainValidationException.class, () ->
                validator.validate(new AcademicClass("   ", "Orientacao a Objetos")));
    }

    @Test
    void classWithBlankTitleFailsValidation() {
        assertThrows(DomainValidationException.class, () ->
                validator.validate(new AcademicClass("CC101", "")));
    }

    @Test
    void validAssessmentPassesValidation() {
        assertDoesNotThrow(() -> validator.validate(new Exam("Prova 1", 10.0, 0.4)));
    }

    @Test
    void assessmentWithInvalidValueFailsValidation() {
        assertThrows(DomainValidationException.class, () ->
                validator.validate(new Exam("Prova 1", -5.0, 0.4)));
    }

    @Test
    void assessmentWithInvalidWeightFailsValidation() {
        assertThrows(DomainValidationException.class, () ->
                validator.validate(new Exam("Prova 1", 10.0, 1.5)));
    }
}
