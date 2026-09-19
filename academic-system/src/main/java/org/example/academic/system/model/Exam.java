package org.example.academic.system.model;

import lombok.NoArgsConstructor;

/** Avaliacao do tipo prova. */
@NoArgsConstructor
public class Exam extends Assessment {

    public Exam(String description, Double value, Double weight) {
        super(description, value, weight);
    }

    @Override
    public AssessmentType getType() {
        return AssessmentType.EXAM;
    }
}
