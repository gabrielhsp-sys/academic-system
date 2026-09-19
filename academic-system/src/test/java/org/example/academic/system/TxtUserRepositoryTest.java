package org.example.academic.system;

import org.example.academic.system.exception.PersistenceOperationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.repository.TxtUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TxtUserRepositoryTest {

    @TempDir
    Path tempDir;

    @Test
    void validUtf8UserFileIsLoaded() throws IOException {
        Path file = tempDir.resolve("users.txt");
        Files.writeString(file, "professor;senha123;PROFESSOR\n", StandardCharsets.UTF_8);

        TxtUserRepository repository = new TxtUserRepository(file);

        assertTrue(repository.findByUsername("professor").isPresent());
        assertEquals(Role.PROFESSOR, repository.findByUsername("professor").orElseThrow().getRole());
    }

    @Test
    void invalidRoleUsesControlledPersistenceException() throws IOException {
        Path file = tempDir.resolve("users-invalid-role.txt");
        Files.writeString(file, "usuario;senha;PAPEL_INVALIDO\n", StandardCharsets.UTF_8);

        PersistenceOperationException error = assertThrows(
                PersistenceOperationException.class,
                () -> new TxtUserRepository(file));

        assertTrue(error.getMessage().contains("linha 1"));
    }

    @Test
    void malformedRecordUsesControlledPersistenceException() throws IOException {
        Path file = tempDir.resolve("users-malformed.txt");
        Files.writeString(file, "usuario;senha\n", StandardCharsets.UTF_8);

        PersistenceOperationException error = assertThrows(
                PersistenceOperationException.class,
                () -> new TxtUserRepository(file));

        assertTrue(error.getMessage().contains("Linha de usuario invalida (1)"));
    }
}
