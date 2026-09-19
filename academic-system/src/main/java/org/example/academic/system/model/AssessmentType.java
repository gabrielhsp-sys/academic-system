package org.example.academic.system.model;

/**
 * Tipos de avaliacao suportados pelo sistema (US-2361).
 */
public enum AssessmentType {
    EXAM("Prova"),
    PRACTICAL_ASSIGNMENT("Trabalho Pratico"),
    SEMINAR("Seminario"),
    ASSIGNMENT("Tarefa");

    private final String label;

    AssessmentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
