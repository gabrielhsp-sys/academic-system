package org.example.academic.system.repository;

/** Tipos de persistencia suportados pelo sistema (US-2372). */
public enum PersistenceType {
    TXT("txt"),
    XML("xml"),
    JSON("json");

    private final String extension;

    PersistenceType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
