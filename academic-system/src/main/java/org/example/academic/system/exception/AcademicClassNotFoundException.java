package org.example.academic.system.exception;

/** Lancada quando uma turma nao e encontrada pelo codigo informado. */
public class AcademicClassNotFoundException extends AcademicSystemException {

    public AcademicClassNotFoundException(String code) {
        super("Turma nao encontrada para o codigo: " + code);
    }
}
