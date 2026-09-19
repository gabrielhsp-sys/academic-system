package org.example.academic.system.report;

import org.example.academic.system.repository.PersistenceType;

/** Gera o relatorio de configuracao de persistencia (US-2377). */
public class PersistenceConfigurationReportGenerator {

    public String generate(PersistenceType currentType, String targetFile) {
        StringBuilder report = new StringBuilder();
        report.append("===== RELATORIO DE CONFIGURACAO DE PERSISTENCIA =====\n");
        report.append("Tipo de persistencia configurado: ").append(currentType.name()).append("\n");
        report.append("Arquivo de destino: ").append(targetFile).append("\n");
        report.append("Tipos suportados: ");
        PersistenceType[] types = PersistenceType.values();
        for (int i = 0; i < types.length; i++) {
            report.append(types[i].name());
            if (i < types.length - 1) {
                report.append(", ");
            }
        }
        report.append("\n");
        return report.toString();
    }
}
