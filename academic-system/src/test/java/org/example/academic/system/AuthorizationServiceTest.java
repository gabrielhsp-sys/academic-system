package org.example.academic.system;

import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.Session;
import org.example.academic.system.security.SystemOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Testes de autorizacao baseada em papeis (US-2387). */
class AuthorizationServiceTest {

    private final AuthorizationService service = new AuthorizationService();

    private Session adminSession() {
        return new Session(new User("admin", "x", Role.ADMIN));
    }

    private Session professorSession() {
        return new Session(new User("professor", "x", Role.PROFESSOR));
    }

    @Test
    void adminIsAuthorizedForAdministratorOperations() {
        assertDoesNotThrow(() -> service.authorize(adminSession(), SystemOperation.REGISTER_CLASS));
        assertDoesNotThrow(() -> service.authorize(adminSession(), SystemOperation.SAVE_DATA));
        assertDoesNotThrow(() -> service.authorize(adminSession(), SystemOperation.CONFIGURE_PERSISTENCE));
    }

    @Test
    void professorIsNotAuthorizedForAdministratorOperations() {
        assertThrows(AuthorizationException.class,
                () -> service.authorize(professorSession(), SystemOperation.REGISTER_CLASS));
    }

    @Test
    void unauthorizedAccessThrowsAuthorizationException() {
        assertThrows(AuthorizationException.class,
                () -> service.authorize(professorSession(), SystemOperation.SAVE_DATA));
        assertThrows(AuthorizationException.class,
                () -> service.authorize(professorSession(), SystemOperation.GENERATE_PERSISTENCE_REPORT));
    }

    @Test
    void professorIsAuthorizedForAssessmentRegistration() {
        assertDoesNotThrow(() ->
                service.authorize(professorSession(), SystemOperation.REGISTER_ASSESSMENT));
    }

    @Test
    void inactiveSessionIsRejected() {
        Session session = adminSession();
        session.invalidate();
        assertThrows(AuthorizationException.class,
                () -> service.authorize(session, SystemOperation.VIEW_ACADEMIC_DATA));
    }
}
