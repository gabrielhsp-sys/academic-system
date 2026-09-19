package org.example.academic.system.exception;

/** Lancada quando o usuario digita um valor que nao e um numero valido. */
public class InvalidNumberInputException extends KeyboardInputException {

    public InvalidNumberInputException(String input, Throwable cause) {
        super("Entrada numerica invalida: \"" + input + "\"", cause);
    }
}
