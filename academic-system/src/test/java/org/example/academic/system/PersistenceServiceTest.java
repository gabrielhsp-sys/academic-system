package org.example.academic.system;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.service.PersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes do PersistenceService (TUS-2403). */
class PersistenceServiceTest {

    @TempDir
    Path tempDir;

    private final AcademicSystem academicSystem = AcademicSystem.getInstance();
    private PersistenceService service;

    @BeforeEach
    void setUp() {
        academicSystem.reset();
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicClass.addAssessment(new Exam("Prova 1", 10.0, 0.4));
        academicSystem.addClass(academicClass);
        service = new PersistenceService(academicSystem, tempDir);
    }

    @Test
    void savesUsingDefaultTxtRepository() throws IOException {
        assertEquals(PersistenceType.TXT, service.getCurrentType());
        Path target = service.save();
        assertTrue(target.toString().endsWith(".txt"));
        assertTrue(Files.exists(target));
        assertTrue(Files.readString(target).contains("CC101"));
    }

    @Test
    void changesPersistenceTypeToXmlAndSaves() throws IOException {
        service.changePersistenceType(PersistenceType.XML);
        Path target = service.save();
        assertTrue(target.toString().endsWith(".xml"));
        assertTrue(Files.exists(target));
        assertTrue(Files.readString(target).contains("CC101"));
    }

    @Test
    void changesPersistenceTypeToJsonAndSaves() throws IOException {
        service.changePersistenceType(PersistenceType.JSON);
        Path target = service.save();
        assertTrue(target.toString().endsWith(".json"));
        assertTrue(Files.exists(target));
        assertTrue(Files.readString(target).contains("CC101"));
    }

    @Test
    void parsesPersistenceTypeFromText() {
        assertEquals(PersistenceType.XML, service.parseType("xml"));
        assertEquals(PersistenceType.JSON, service.parseType(" JSON "));
        assertThrows(AcademicSystemException.class, () -> service.parseType("yaml"));
    }
}
