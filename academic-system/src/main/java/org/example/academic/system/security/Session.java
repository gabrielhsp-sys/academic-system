package org.example.academic.system.security;

import lombok.Getter;
import org.example.academic.system.model.User;

import java.time.LocalDateTime;

/**
 * Sessao de um usuario autenticado. Criada no login e invalidada
 * no logout (US-2379), encerrando o acesso as operacoes protegidas.
 */
@Getter
public class Session {

    private final User user;
    private final LocalDateTime createdAt;
    private boolean active;

    public Session(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    public void invalidate() {
        this.active = false;
    }
}
