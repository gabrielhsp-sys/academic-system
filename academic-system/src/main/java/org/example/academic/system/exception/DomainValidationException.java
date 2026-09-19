package org.example.academic.system.exception;

/**
 * Converte violacoes do Jakarta Bean Validation em excecao de dominio
 * academico (TUS-2371, AC7).
 */
public class DomainValidationException extends AcademicSystemException {

    public DomainValidationException(String message) {
        super(message);
    }
}
