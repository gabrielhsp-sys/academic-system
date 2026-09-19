package org.example.academic.system.model;

import lombok.NoArgsConstructor;

/** Avaliacao do tipo tarefa. */
@NoArgsConstructor
public class Assignment extends Assessment {

    public Assignment(String description, Double value, Double weight) {
        super(description, value, weight);
    }

    @Override
    public AssessmentType getType() {
        return AssessmentType.ASSIGNMENT;
    }
}
