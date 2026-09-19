package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicClass;

import java.nio.file.Path;
import java.util.List;

/**
 * Abstracao de persistencia de turmas (Repository + Strategy).
 * Cada implementacao concreta (TXT, XML, JSON) e uma estrategia
 * intercambiavel selecionada em tempo de execucao pelo
 * PersistenceService, sem que o dominio conheca o formato.
 */
public interface ClassRepository {

    void save(List<AcademicClass> classes, Path target);

    PersistenceType getType();
}
