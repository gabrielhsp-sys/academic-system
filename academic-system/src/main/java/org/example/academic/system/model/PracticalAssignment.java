package org.example.academic.system.model;

import lombok.NoArgsConstructor;

/** Avaliacao do tipo trabalho pratico. */
@NoArgsConstructor
public class PracticalAssignment extends Assessment {

    public PracticalAssignment(String description, Double value, Double weight) {
        super(description, value, weight);
    }

    @Override
    public AssessmentType getType() {
        return AssessmentType.PRACTICAL_ASSIGNMENT;
    }
}
