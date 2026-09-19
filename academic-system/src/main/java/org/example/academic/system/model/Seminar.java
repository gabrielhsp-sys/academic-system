package org.example.academic.system.model;

import lombok.NoArgsConstructor;

/** Avaliacao do tipo seminario. */
@NoArgsConstructor
public class Seminar extends Assessment {

    public Seminar(String description, Double value, Double weight) {
        super(description, value, weight);
    }

    @Override
    public AssessmentType getType() {
        return AssessmentType.SEMINAR;
    }
}
