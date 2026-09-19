package org.example.academic.system.repository;

import org.example.academic.system.model.User;

import java.util.Optional;

/** Abstracao de acesso aos usuarios do sistema (US-2366). */
public interface UserRepository {

    Optional<User> findByUsername(String username);
}
