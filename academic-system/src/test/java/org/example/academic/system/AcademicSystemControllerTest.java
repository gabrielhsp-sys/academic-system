package org.example.academic.system;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.Session;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * Testes de delegacao do AcademicSystemController (TUS-2405).
 * Os services sao mocks do Mockito; o AuthorizationService e real,
 * garantindo que as regras de autorizacao sejam preservadas.
 */
@ExtendWith(MockitoExtension.class)
class AcademicSystemControllerTest {

    @Mock
    private ClassService classService;
    @Mock
    private AssessmentService assessmentService;
    @Mock
    private PersistenceService persistenceService;
    @Mock
    private ReportService reportService;

    private final AuthorizationService authorizationService = new AuthorizationService();

    private AcademicSystemController controllerFor(Role role) {
        Session session = new Session(new User(role.name().toLowerCase(), "x", role));
        return new AcademicSystemController(session, classService, assessmentService,
                persistenceService, reportService, authorizationService);
    }

    @Test
    void delegatesClassRegistrationToClassService() {
        controllerFor(Role.ADMIN).registerClass("CC101", "Orientacao a Objetos");
        verify(classService).registerClass("CC101", "Orientacao a Objetos");
    }

    @Test
    void preservesAuthorizationRulesForClassRegistration() {
        assertThrows(AuthorizationException.class,
                () -> controllerFor(Role.PROFESSOR).registerClass("CC101", "POO"));
        verifyNoInteractions(classService);
    }

    @Test
    void delegatesAssessmentRegistrationToAssessmentService() {
        controllerFor(Role.PROFESSOR)
                .registerAssessment("CC101", "EXAM", "Prova 1", 10.0, 0.4);
        verify(assessmentService)
                .registerAssessment("CC101", "EXAM", "Prova 1", 10.0, 0.4);
    }

    @Test
    void preservesAuthorizationRulesForAssessmentRegistration() {
        assertThrows(AuthorizationException.class, () -> controllerFor(Role.ADMIN)
                .registerAssessment("CC101", "EXAM", "Prova 1", 10.0, 0.4));
        verifyNoInteractions(assessmentService);
    }

    @Test
    void delegatesSaveToPersistenceService() {
        controllerFor(Role.ADMIN).saveAcademicData();
        verify(persistenceService).save();
    }

    @Test
    void delegatesPersistenceConfigurationToPersistenceService() {
        when(persistenceService.parseType("XML")).thenReturn(PersistenceType.XML);
        controllerFor(Role.ADMIN).configurePersistence("XML");
        verify(persistenceService).changePersistenceType(PersistenceType.XML);
    }

    @Test
    void preservesAuthorizationRulesForPersistenceConfiguration() {
        assertThrows(AuthorizationException.class,
                () -> controllerFor(Role.PROFESSOR).configurePersistence("XML"));
        verifyNoInteractions(persistenceService);
    }

    @Test
    void delegatesReportGenerationToReportService() {
        controllerFor(Role.ADMIN).generateClassAssessmentSummaryReport();
        verify(reportService).generateClassAssessmentSummaryReport(Role.ADMIN);
    }
}
