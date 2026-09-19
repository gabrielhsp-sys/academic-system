package org.example.academic.system.report;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

import java.util.List;

/** Gera o relatorio de avaliacoes por turma (US-2375). Operacao somente leitura. */
public class ClassAssessmentSummaryReportGenerator {

    public String generate(List<AcademicClass> classes) {
        StringBuilder report = new StringBuilder();
        report.append("===== RELATORIO DE AVALIACOES POR TURMA =====\n");
        if (classes.isEmpty()) {
            report.append("Nenhuma turma cadastrada.\n");
            return report.toString();
        }
        for (AcademicClass academicClass : classes) {
            report.append("\nTurma: ").append(academicClass.getCode())
                  .append(" - ").append(academicClass.getTitle()).append("\n");
            if (academicClass.getAssessments().isEmpty()) {
                report.append("  (sem avaliacoes cadastradas)\n");
                continue;
            }
            for (Assessment assessment : academicClass.getAssessments()) {
                report.append("  [").append(assessment.getType().getLabel()).append("] ")
                      .append(assessment.getDescription())
                      .append(" | Valor: ").append(assessment.getValue())
                      .append(" | Peso: ").append(assessment.getWeight())
                      .append("\n");
            }
        }
        return report.toString();
    }
}
