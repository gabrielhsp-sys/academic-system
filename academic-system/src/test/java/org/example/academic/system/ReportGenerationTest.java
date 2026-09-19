package org.example.academic.system;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.model.Seminar;
import org.example.academic.system.report.AssessmentWeightReportGenerator;
import org.example.academic.system.report.ClassAssessmentSummaryReportGenerator;
import org.example.academic.system.report.PersistenceConfigurationReportGenerator;
import org.example.academic.system.repository.PersistenceType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes de geracao de relatorios (US-2388). */
class ReportGenerationTest {

    @Test
    void summaryReportIncludesClassAndAssessmentData() {
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicClass.addAssessment(new Exam("Prova 1", 10.0, 0.4));

        String report = new ClassAssessmentSummaryReportGenerator()
                .generate(List.of(academicClass));

        assertTrue(report.contains("CC101"));
        assertTrue(report.contains("Orientacao a Objetos"));
        assertTrue(report.contains("Prova"));
        assertTrue(report.contains("10.0"));
        assertTrue(report.contains("0.4"));
    }

    @Test
    void summaryReportHandlesEmptySystem() {
        String report = new ClassAssessmentSummaryReportGenerator().generate(List.of());
        assertNotNull(report);
        assertTrue(report.contains("Nenhuma turma cadastrada"));
    }

    @Test
    void weightReportCalculatesTotalWeightForValidComposition() {
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicClass.addAssessment(new Exam("Prova 1", 10.0, 0.4));
        academicClass.addAssessment(new Seminar("Seminario", 10.0, 0.6));

        String report = new AssessmentWeightReportGenerator().generate(List.of(academicClass));

        assertTrue(report.contains("1.00"));
        assertTrue(report.contains("COMPOSICAO VALIDA"));
    }

    @Test
    void weightReportFlagsInvalidComposition() {
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicClass.addAssessment(new Exam("Prova 1", 10.0, 0.4));

        String report = new AssessmentWeightReportGenerator().generate(List.of(academicClass));

        assertTrue(report.contains("0.40"));
        assertTrue(report.contains("COMPOSICAO INVALIDA"));
    }

    @Test
    void weightReportShowsZeroForClassWithoutAssessments() {
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        String report = new AssessmentWeightReportGenerator().generate(List.of(academicClass));
        assertTrue(report.contains("0.0"));
    }

    @Test
    void persistenceConfigurationReportShowsSelectedType() {
        String report = new PersistenceConfigurationReportGenerator()
                .generate(PersistenceType.XML, "data/academic-data.xml");
        assertTrue(report.contains("XML"));
        assertTrue(report.contains("data/academic-data.xml"));
    }
}
