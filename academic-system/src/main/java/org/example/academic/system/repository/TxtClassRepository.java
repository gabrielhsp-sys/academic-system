package org.example.academic.system.repository;

import org.example.academic.system.exception.PersistenceOperationException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Estrategia de persistencia em arquivo TXT (TUS-2362). */
public class TxtClassRepository implements ClassRepository {

    @Override
    public void save(List<AcademicClass> classes, Path target) {
        try (BufferedWriter writer = Files.newBufferedWriter(target)) {
            for (AcademicClass academicClass : classes) {
                writer.write("TURMA;" + academicClass.getCode() + ";" + academicClass.getTitle());
                writer.newLine();
                for (Assessment assessment : academicClass.getAssessments()) {
                    writer.write("AVALIACAO;" + assessment.getType().name()
                            + ";" + assessment.getDescription()
                            + ";" + assessment.getValue()
                            + ";" + assessment.getWeight());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new PersistenceOperationException("Falha ao salvar o arquivo TXT: " + target, e);
        }
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.TXT;
    }
}
