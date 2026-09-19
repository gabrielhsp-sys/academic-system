package org.example.academic.system.exception;

/**
 * Superclasse comum das excecoes de seguranca (US-2369, AC3).
 * O prefixo Academic evita colisao com java.lang.SecurityException.
 */
public class AcademicSecurityException extends RuntimeException {

    public AcademicSecurityException(String message) {
        super(message);
    }
}
