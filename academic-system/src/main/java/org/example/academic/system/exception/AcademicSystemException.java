package org.example.academic.system.exception;

/**
 * Superclasse comum das excecoes de dominio academico (US-2367).
 * Estende RuntimeException para nao poluir as assinaturas dos metodos
 * com throws, mantendo o tratamento centralizado no Main e nas views.
 */
public class AcademicSystemException extends RuntimeException {

    public AcademicSystemException(String message) {
        super(message);
    }

    public AcademicSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
