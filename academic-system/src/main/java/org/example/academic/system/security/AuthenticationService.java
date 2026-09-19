package org.example.academic.system.security;

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Autenticacao de usuarios (US-2366). As credenciais sao verificadas
 * contra o repositorio TXT configurado (AC5). Eventos de login e logout
 * sao auditados (TUS-2391) e senhas nunca aparecem nos logs (AC6).
 */
public class AuthenticationService {

    private static final Logger AUDIT = LoggerFactory.getLogger("AUDIT");

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            AUDIT.warn("Falha de autenticacao para o usuario [{}]", username);
            throw new AuthenticationException("Usuario ou senha invalidos.");
        }
        AUDIT.info("Login realizado com sucesso: usuario [{}], papel [{}]",
                username, user.get().getRole());
        return user.get();
    }

    public void logout(Session session) {
        if (session != null && session.isActive()) {
            session.invalidate();
            AUDIT.info("Logout realizado: usuario [{}]", session.getUser().getUsername());
        }
    }
}
