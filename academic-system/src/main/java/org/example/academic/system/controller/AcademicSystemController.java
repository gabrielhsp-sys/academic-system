package org.example.academic.system.controller;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.Session;
import org.example.academic.system.security.SystemOperation;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;

import java.nio.file.Path;
import java.util.List;

/**
 * Controller da aplicacao (GRASP: Controller). Atua apenas como camada
 * de coordenacao (TUS-2400): verifica a autorizacao da sessao e delega
 * cada operacao ao service responsavel, sem conter logica de negocio.
 */
public class AcademicSystemController {

    private final Session session;
    private final ClassService classService;
    private final AssessmentService assessmentService;
    private final PersistenceService persistenceService;
    private final ReportService reportService;
    private final AuthorizationService authorizationService;

    public AcademicSystemController(Session session,
                                    ClassService classService,
                                    AssessmentService assessmentService,
                                    PersistenceService persistenceService,
                                    ReportService reportService,
                                    AuthorizationService authorizationService) {
        this.session = session;
        this.classService = classService;
        this.assessmentService = assessmentService;
        this.persistenceService = persistenceService;
        this.reportService = reportService;
        this.authorizationService = authorizationService;
    }

    public AcademicClass registerClass(String code, String title) {
        authorizationService.authorize(session, SystemOperation.REGISTER_CLASS);
        return classService.registerClass(code, title);
    }

    public Assessment registerAssessment(String classCode, String typeName,
                                         String description, Double value, Double weight) {
        authorizationService.authorize(session, SystemOperation.REGISTER_ASSESSMENT);
        return assessmentService.registerAssessment(classCode, typeName, description, value, weight);
    }

    public List<AcademicClass> listClasses() {
        authorizationService.authorize(session, SystemOperation.VIEW_ACADEMIC_DATA);
        return classService.listClasses();
    }

    public Path saveAcademicData() {
        authorizationService.authorize(session, SystemOperation.SAVE_DATA);
        return persistenceService.save();
    }

    public void configurePersistence(String typeName) {
        authorizationService.authorize(session, SystemOperation.CONFIGURE_PERSISTENCE);
        PersistenceType type = persistenceService.parseType(typeName);
        persistenceService.changePersistenceType(type);
    }

    public PersistenceType getCurrentPersistenceType() {
        return persistenceService.getCurrentType();
    }

    public String generateClassAssessmentSummaryReport() {
        authorizationService.authorize(session, SystemOperation.GENERATE_SUMMARY_REPORT);
        return reportService.generateClassAssessmentSummaryReport(session.getUser().getRole());
    }

    public String generateAssessmentWeightReport() {
        authorizationService.authorize(session, SystemOperation.GENERATE_WEIGHT_REPORT);
        return reportService.generateAssessmentWeightReport(session.getUser().getRole());
    }

    public String generatePersistenceConfigurationReport() {
        authorizationService.authorize(session, SystemOperation.GENERATE_PERSISTENCE_REPORT);
        return reportService.generatePersistenceConfigurationReport(session.getUser().getRole());
    }

    public Session getSession() {
        return session;
    }
}
