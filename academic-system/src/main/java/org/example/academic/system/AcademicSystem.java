package org.example.academic.system;

import org.example.academic.system.model.AcademicClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Raiz do dominio academico, implementada como Singleton (US-0000).
 *
 * A instancia unica e criada de forma eager (no carregamento da classe),
 * o que garante thread safety sem necessidade de sincronizacao explicita.
 * O construtor privado impede a criacao de instancias adicionais.
 */
public final class AcademicSystem {

    private static final AcademicSystem INSTANCE = new AcademicSystem();

    private final List<AcademicClass> classes = new ArrayList<>();

    private AcademicSystem() {
    }

    public static AcademicSystem getInstance() {
        return INSTANCE;
    }

    public void addClass(AcademicClass academicClass) {
        classes.add(academicClass);
    }

    /** Visao somente leitura da lista de turmas registradas. */
    public List<AcademicClass> getClasses() {
        return Collections.unmodifiableList(classes);
    }

    public Optional<AcademicClass> findClassByCode(String code) {
        return classes.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst();
    }

    /** Limpa o estado do sistema. Utilizado pelos testes automatizados. */
    public void reset() {
        classes.clear();
    }
}
