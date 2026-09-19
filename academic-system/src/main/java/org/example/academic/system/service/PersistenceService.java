package org.example.academic.system.service;

import org.example.academic.system.AcademicSystem;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.PersistenceOperationException;
import org.example.academic.system.repository.ClassRepository;
import org.example.academic.system.repository.JsonClassRepository;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.repository.TxtClassRepository;
import org.example.academic.system.repository.XmlClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

/**
 * Isola as preocupacoes de persistencia (TUS-2398): mantem a estrategia
 * ativa (US-2372), seleciona o repositorio correspondente e executa o
 * salvamento, registrando as operacoes para auditoria (TUS-2393).
 */
public class PersistenceService {

    private static final Logger AUDIT = LoggerFactory.getLogger("AUDIT");

    public static final String FILE_BASE_NAME = "academic-data";

    private final AcademicSystem academicSystem;
    private final Path baseDirectory;
    private final Map<PersistenceType, ClassRepository> repositories =
            new EnumMap<>(PersistenceType.class);

    private PersistenceType currentType = PersistenceType.TXT;

    public PersistenceService(AcademicSystem academicSystem) {
        this(academicSystem, Path.of("data"));
    }

    public PersistenceService(AcademicSystem academicSystem, Path baseDirectory) {
        this.academicSystem = academicSystem;
        this.baseDirectory = baseDirectory;
        repositories.put(PersistenceType.TXT, new TxtClassRepository());
        repositories.put(PersistenceType.XML, new XmlClassRepository());
        repositories.put(PersistenceType.JSON, new JsonClassRepository());
    }

    public void changePersistenceType(PersistenceType type) {
        this.currentType = type;
        AUDIT.info("Tipo de persistencia alterado para [{}]", type);
    }

    public PersistenceType parseType(String name) {
        try {
            return PersistenceType.valueOf(name.trim().toUpperCase());
        } catch (RuntimeException e) {
            throw new AcademicSystemException("Tipo de persistencia invalido: " + name);
        }
    }

    public PersistenceType getCurrentType() {
        return currentType;
    }

    public Path getTargetFile() {
        return baseDirectory.resolve(FILE_BASE_NAME + "." + currentType.getExtension());
    }

    public Path save() {
        try {
            Files.createDirectories(baseDirectory);
        } catch (IOException e) {
            throw new PersistenceOperationException(
                    "Falha ao preparar o diretorio de dados: " + baseDirectory, e);
        }
        Path target = getTargetFile();
        repositories.get(currentType).save(academicSystem.getClasses(), target);
        AUDIT.info("Dados academicos salvos em formato [{}] no arquivo [{}] ({} turmas)",
                currentType, target, academicSystem.getClasses().size());
        return target;
    }
}
