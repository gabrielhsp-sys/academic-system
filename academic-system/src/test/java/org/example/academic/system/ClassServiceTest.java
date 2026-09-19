package org.example.academic.system;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.DuplicateClassException;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.validation.DomainValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes do ClassService (TUS-2401). */
class ClassServiceTest {

    private final AcademicSystem academicSystem = AcademicSystem.getInstance();
    private ClassService service;

    @BeforeEach
    void setUp() {
        academicSystem.reset();
        service = new ClassService(academicSystem, new DomainValidator());
    }

    @Test
    void registersValidClass() {
        service.registerClass("CC101", "Orientacao a Objetos");
        assertEquals(1, academicSystem.getClasses().size());
    }

    @Test
    void registeredClassIsStoredInAcademicSystem() {
        service.registerClass("CC101", "Orientacao a Objetos");
        assertTrue(academicSystem.findClassByCode("CC101").isPresent());
    }

    @Test
    void invalidClassDataThrowsAcademicSystemException() {
        assertThrows(AcademicSystemException.class,
                () -> service.registerClass("", "Orientacao a Objetos"));
        assertTrue(academicSystem.getClasses().isEmpty());
    }

    @Test
    void duplicateClassCodeIsRejected() {
        service.registerClass("CC101", "Orientacao a Objetos");
        assertThrows(DuplicateClassException.class,
                () -> service.registerClass("CC101", "Outra turma"));
        assertEquals(1, academicSystem.getClasses().size());
    }
}
