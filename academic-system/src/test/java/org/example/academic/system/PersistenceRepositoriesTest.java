package org.example.academic.system;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.repository.JsonClassRepository;
import org.example.academic.system.repository.TxtClassRepository;
import org.example.academic.system.repository.XmlClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes dos repositorios de persistencia TXT, XML e JSON (US-2389). */
class PersistenceRepositoriesTest {

    @TempDir
    Path tempDir;

    private List<AcademicClass> sampleData() {
        AcademicClass academicClass = new AcademicClass("CC101", "Orientacao a Objetos");
        academicClass.addAssessment(new Exam("Prova 1", 10.0, 0.4));
        return List.of(academicClass);
    }

    @Test
    void txtRepositoryGeneratesTxtFileWithData() throws IOException {
        Path file = tempDir.resolve("academic-data.txt");
        new TxtClassRepository().save(sampleData(), file);

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("CC101"));
        assertTrue(content.contains("Orientacao a Objetos"));
        assertTrue(content.contains("EXAM"));
        assertTrue(content.contains("0.4"));
    }

    @Test
    void xmlRepositoryGeneratesXmlFileWithData() throws IOException {
        Path file = tempDir.resolve("academic-data.xml");
        new XmlClassRepository().save(sampleData(), file);

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("<class"));
        assertTrue(content.contains("CC101"));
        assertTrue(content.contains("EXAM"));
    }

    @Test
    void jsonRepositoryGeneratesJsonFileWithData() throws IOException {
        Path file = tempDir.resolve("academic-data.json");
        new JsonClassRepository().save(sampleData(), file);

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("\"code\""));
        assertTrue(content.contains("CC101"));
        assertTrue(content.contains("EXAM"));
    }
}
