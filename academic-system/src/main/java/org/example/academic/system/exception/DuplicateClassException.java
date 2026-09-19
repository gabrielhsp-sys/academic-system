package org.example.academic.system.exception;

/** Lancada ao tentar cadastrar uma turma com codigo ja existente. */
public class DuplicateClassException extends AcademicSystemException {

    public DuplicateClassException(String code) {
        super("Ja existe uma turma cadastrada com o codigo: " + code);
    }
}
