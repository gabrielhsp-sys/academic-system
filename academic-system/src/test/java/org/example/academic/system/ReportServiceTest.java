package org.example.academic.system;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.model.Role;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes do ReportService (TUS-2404). */
class ReportServiceTest {

    @TempDir
    Path tempDir;

    private final AcademicSystem academicSystem = AcademicSystem.getInstance();
    private ReportService service;

    @BeforeEach
    void setUp() {
        academicSystem.reset();
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicClass.addAssessment(new Exam("Prova 1", 10.0, 0.4));
        academicSystem.addClass(academicClass);
        service = new ReportService(academicSystem,
                new PersistenceService(academicSystem, tempDir));
    }

    @Test
    void generatesClassAssessmentSummaryReport() {
        String report = service.generateClassAssessmentSummaryReport(Role.ADMIN);
        assertTrue(report.contains("CC101"));
        assertTrue(report.contains("Prova 1"));
    }

    @Test
    void generatesAssessmentWeightReport() {
        String report = service.generateAssessmentWeightReport(Role.PROFESSOR);
        assertTrue(report.contains("Peso total"));
        assertTrue(report.contains("CC101"));
    }

    @Test
    void generatesPersistenceConfigurationReport() {
        String report = service.generatePersistenceConfigurationReport(Role.ADMIN);
        assertTrue(report.contains("TXT"));
    }
}
