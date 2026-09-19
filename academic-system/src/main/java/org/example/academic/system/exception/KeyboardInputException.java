package org.example.academic.system.exception;

/**
 * Superclasse comum das excecoes de entrada de teclado (US-2368).
 * Mantida separada das excecoes de dominio e de seguranca.
 */
public class KeyboardInputException extends RuntimeException {

    public KeyboardInputException(String message) {
        super(message);
    }

    public KeyboardInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
