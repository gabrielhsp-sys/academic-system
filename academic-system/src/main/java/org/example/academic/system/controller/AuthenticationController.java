package org.example.academic.system.controller;

import org.example.academic.system.model.User;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.security.Session;

/**
 * Controller de autenticacao para a interface JavaFX (TUS-2414).
 * Desacopla a camada grafica do AuthenticationService, mantendo a GUI
 * livre de logica de negocio e reutilizavel por novas telas (AC10).
 */
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /** Delegacao pura: excecoes de autenticacao sao propagadas (AC6). */
    public User authenticate(String username, String password) {
        return authenticationService.authenticate(username, password);
    }

    public void logout(Session session) {
        authenticationService.logout(session);
    }
}
