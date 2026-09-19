package org.example.academic.system.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.example.academic.system.exception.DomainValidationException;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Componente reutilizavel que centraliza a validacao de dominio
 * com Jakarta Bean Validation (TUS-2371, AC6).
 *
 * As violacoes encontradas sao convertidas em DomainValidationException,
 * que pertence a hierarquia de excecoes do dominio academico (AC7).
 */
public class DomainValidator {

    private final Validator validator;

    public DomainValidator() {
        // ParameterMessageInterpolator dispensa uma implementacao de
        // Expression Language, ja que as mensagens sao literais.
        this.validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();
    }

    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .sorted()
                    .collect(Collectors.joining("; "));
            throw new DomainValidationException(message);
        }
    }
}
