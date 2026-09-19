package org.example.academic.system.repository;

import org.example.academic.system.exception.PersistenceOperationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio TXT de usuarios (US-2366, AC5). Cada linha do arquivo
 * segue o formato username;senha;PAPEL. Por padrao, os usuarios sao
 * carregados do recurso /users.txt empacotado na aplicacao.
 */
public class TxtUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    /** Carrega os usuarios do recurso padrao do classpath. */
    public TxtUserRepository() {
        try (InputStream stream = TxtUserRepository.class.getResourceAsStream("/users.txt")) {
            if (stream == null) {
                throw new PersistenceOperationException(
                        "Arquivo de usuarios /users.txt nao encontrado no classpath", null);
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    parseLine(line);
                }
            }
        } catch (IOException e) {
            throw new PersistenceOperationException("Falha ao carregar o arquivo de usuarios", e);
        }
    }

    /** Carrega os usuarios de um arquivo externo (util para testes). */
    public TxtUserRepository(Path file) {
        try {
            for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
                parseLine(line);
            }
        } catch (IOException e) {
            throw new PersistenceOperationException("Falha ao carregar o arquivo de usuarios: " + file, e);
        }
    }

    private void parseLine(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty() || trimmed.startsWith("#")) {
            return;
        }
        String[] parts = trimmed.split(";");
        if (parts.length == 3) {
            users.add(new User(parts[0].trim(), parts[1].trim(),
                    Role.valueOf(parts[2].trim().toUpperCase())));
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}
