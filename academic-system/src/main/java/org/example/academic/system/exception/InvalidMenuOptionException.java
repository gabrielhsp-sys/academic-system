package org.example.academic.system.exception;

/** Lancada quando o usuario seleciona uma opcao inexistente do menu. */
public class InvalidMenuOptionException extends KeyboardInputException {

    public InvalidMenuOptionException(int option) {
        super("Opcao de menu invalida: " + option);
    }
}
