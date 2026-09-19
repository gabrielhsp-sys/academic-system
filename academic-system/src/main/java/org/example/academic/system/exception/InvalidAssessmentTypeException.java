package org.example.academic.system.exception;

/** Lancada quando o tipo de avaliacao informado nao e suportado. */
public class InvalidAssessmentTypeException extends AcademicSystemException {

    public InvalidAssessmentTypeException(String type) {
        super("Tipo de avaliacao invalido: " + type);
    }
}
