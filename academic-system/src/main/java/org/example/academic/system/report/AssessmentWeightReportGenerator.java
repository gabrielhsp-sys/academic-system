package org.example.academic.system.report;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

import java.util.List;
import java.util.Locale;

/**
 * Gera o relatorio de pesos das avaliacoes (US-2376). A composicao de
 * uma turma e considerada valida quando a soma dos pesos e igual a 1.0.
 */
public class AssessmentWeightReportGenerator {

    private static final double EXPECTED_TOTAL = 1.0;
    private static final double TOLERANCE = 1e-9;

    public String generate(List<AcademicClass> classes) {
        StringBuilder report = new StringBuilder();
        report.append("===== RELATORIO DE PESOS DAS AVALIACOES =====\n");
        if (classes.isEmpty()) {
            report.append("Nenhuma turma cadastrada.\n");
            return report.toString();
        }
        for (AcademicClass academicClass : classes) {
            double total = academicClass.getAssessments().stream()
                    .mapToDouble(Assessment::getWeight)
                    .sum();
            boolean valid = Math.abs(total - EXPECTED_TOTAL) < TOLERANCE;
            report.append("\nTurma: ").append(academicClass.getCode())
                  .append(" - ").append(academicClass.getTitle()).append("\n")
                  .append("  Peso total: ").append(String.format(Locale.US, "%.2f", total))
                  .append(" -> ")
                  .append(valid ? "COMPOSICAO VALIDA" : "COMPOSICAO INVALIDA (esperado: 1.0)")
                  .append("\n");
        }
        return report.toString();
    }
}
