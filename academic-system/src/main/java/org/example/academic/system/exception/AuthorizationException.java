package org.example.academic.system.exception;

/** Lancada quando o usuario nao tem permissao para a operacao (US-2369, AC2). */
public class AuthorizationException extends AcademicSecurityException {

    public AuthorizationException(String message) {
        super(message);
    }
}
