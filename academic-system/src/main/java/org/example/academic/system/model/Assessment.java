package org.example.academic.system.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Superclasse abstrata da hierarquia de avaliacoes.
 * Demonstra abstracao, heranca e polimorfismo: cada subclasse concreta
 * informa o proprio tipo atraves do metodo polimorfico getType().
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class Assessment {

    @NotBlank(message = "A descricao da avaliacao e obrigatoria")
    private String description;

    @NotNull(message = "O valor da avaliacao e obrigatorio")
    @Positive(message = "O valor da avaliacao deve ser maior que zero")
    private Double value;

    @NotNull(message = "O peso da avaliacao e obrigatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "O peso da avaliacao deve ser maior que zero")
    @DecimalMax(value = "1.0", message = "O peso da avaliacao deve ser no maximo 1.0")
    private Double weight;

    protected Assessment(String description, Double value, Double weight) {
        this.description = description;
        this.value = value;
        this.weight = weight;
    }

    /** Tipo concreto da avaliacao (resolvido polimorficamente). */
    public abstract AssessmentType getType();
}
