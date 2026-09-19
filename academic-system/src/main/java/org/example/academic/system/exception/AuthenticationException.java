package org.example.academic.system.exception;

/** Lancada quando as credenciais de login sao invalidas (US-2369, AC1). */
public class AuthenticationException extends AcademicSecurityException {

    public AuthenticationException(String message) {
        super(message);
    }
}
