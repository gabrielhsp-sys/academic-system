package org.example.academic.system.service;

import org.example.academic.system.AcademicSystem;
import org.example.academic.system.model.Role;
import org.example.academic.system.report.AssessmentWeightReportGenerator;
import org.example.academic.system.report.ClassAssessmentSummaryReportGenerator;
import org.example.academic.system.report.PersistenceConfigurationReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Centraliza a geracao de relatorios (TUS-2399), delegando a montagem
 * do texto aos geradores do pacote report e registrando cada geracao
 * para auditoria com o papel do solicitante (TUS-2394).
 */
public class ReportService {

    private static final Logger AUDIT = LoggerFactory.getLogger("AUDIT");

    private final AcademicSystem academicSystem;
    private final PersistenceService persistenceService;
    private final ClassAssessmentSummaryReportGenerator summaryGenerator =
            new ClassAssessmentSummaryReportGenerator();
    private final AssessmentWeightReportGenerator weightGenerator =
            new AssessmentWeightReportGenerator();
    private final PersistenceConfigurationReportGenerator persistenceGenerator =
            new PersistenceConfigurationReportGenerator();

    public ReportService(AcademicSystem academicSystem, PersistenceService persistenceService) {
        this.academicSystem = academicSystem;
        this.persistenceService = persistenceService;
    }

    public String generateClassAssessmentSummaryReport(Role requesterRole) {
        String report = summaryGenerator.generate(academicSystem.getClasses());
        AUDIT.info("Relatorio de avaliacoes por turma gerado por usuario com papel [{}]", requesterRole);
        return report;
    }

    public String generateAssessmentWeightReport(Role requesterRole) {
        String report = weightGenerator.generate(academicSystem.getClasses());
        AUDIT.info("Relatorio de pesos das avaliacoes gerado por usuario com papel [{}]", requesterRole);
        return report;
    }

    public String generatePersistenceConfigurationReport(Role requesterRole) {
        String report = persistenceGenerator.generate(
                persistenceService.getCurrentType(),
                persistenceService.getTargetFile().toString());
        AUDIT.info("Relatorio de configuracao de persistencia gerado por usuario com papel [{}]", requesterRole);
        return report;
    }
}
