package org.example.academic.system.view;

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.User;
import org.example.academic.system.security.AuthenticationService;

/** Tela de login da interface de linha de comando. */
public class LoginView {

    /** Retorna o usuario autenticado, ou null se o usuario pedir para sair. */
    public User login(KeyboardReader reader, AuthenticationService authenticationService) {
        while (true) {
            System.out.println();
            System.out.println("===== ACADEMIC SYSTEM - LOGIN =====");
            System.out.println("(digite \"sair\" no campo usuario para encerrar)");
            String username = reader.readLine("Usuario: ");
            if (username.equalsIgnoreCase("sair")) {
                return null;
            }
            String password = reader.readLine("Senha: ");
            try {
                return authenticationService.authenticate(username, password);
            } catch (AuthenticationException e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }
}
