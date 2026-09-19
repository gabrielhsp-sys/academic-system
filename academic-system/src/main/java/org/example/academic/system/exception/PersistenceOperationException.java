package org.example.academic.system.exception;

/** Lancada quando uma operacao de persistencia falha. */
public class PersistenceOperationException extends AcademicSystemException {

    public PersistenceOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
