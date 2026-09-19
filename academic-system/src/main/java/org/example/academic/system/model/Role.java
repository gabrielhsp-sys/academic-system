package org.example.academic.system.model;

/**
 * Papeis suportados pelo controle de acesso baseado em papeis (RBAC).
 */
public enum Role {
    ADMIN("Administrador"),
    PROFESSOR("Professor");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
