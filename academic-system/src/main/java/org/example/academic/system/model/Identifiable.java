package org.example.academic.system.model;

/**
 * Contrato para objetos de dominio que possuem identidade propria.
 * A identidade e usada como base para equals e hashCode (TUS-2382).
 */
public interface Identifiable<T> {
    T getId();
}
