package org.example.academic.system;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.KeyboardInputException;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.TxtUserRepository;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.Session;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;
import org.example.academic.system.validation.DomainValidator;
import org.example.academic.system.view.KeyboardReader;
import org.example.academic.system.view.LoginView;
import org.example.academic.system.view.MenuResult;
import org.example.academic.system.view.MenuView;

import java.util.Scanner;

/**
 * Ponto de entrada da aplicacao de linha de comando (US-0000, US-2364).
 * O Main cuida apenas do fluxo de inicializacao, autenticacao e
 * navegacao dos menus (TUS-2370); as operacoes academicas sao
 * delegadas ao AcademicSystemController, e os tres ramos de excecao
 * (entrada de teclado, seguranca e dominio) sao tratados aqui sem
 * encerrar a aplicacao inesperadamente.
 */
public class Main {

    public static void main(String[] args) {
        // Inicializacao do Singleton e montagem das dependencias (US-0000)
        AcademicSystem academicSystem = AcademicSystem.getInstance();
        DomainValidator validator = new DomainValidator();
        AuthenticationService authenticationService =
                new AuthenticationService(new TxtUserRepository());
        AuthorizationService authorizationService = new AuthorizationService();
        ClassService classService = new ClassService(academicSystem, validator);
        AssessmentService assessmentService = new AssessmentService(academicSystem, validator);
        PersistenceService persistenceService = new PersistenceService(academicSystem);
        ReportService reportService = new ReportService(academicSystem, persistenceService);

        KeyboardReader reader = new KeyboardReader(new Scanner(System.in));
        LoginView loginView = new LoginView();
        MenuView menuView = new MenuView(reader);

        System.out.println("===== ACADEMIC SYSTEM =====");
        boolean running = true;
        while (running) {
            User user = loginView.login(reader, authenticationService);
            if (user == null) {
                break;
            }
            Session session = new Session(user);
            AcademicSystemController controller = new AcademicSystemController(
                    session, classService, assessmentService,
                    persistenceService, reportService, authorizationService);

            boolean inSession = true;
            while (inSession) {
                try {
                    MenuResult result = menuView.displayAndExecute(controller, session);
                    switch (result) {
                        case LOGOUT -> {
                            authenticationService.logout(session);
                            inSession = false;
                        }
                        case EXIT -> {
                            authenticationService.logout(session);
                            inSession = false;
                            running = false;
                        }
                        case CONTINUE -> { /* permanece no menu */ }
                    }
                } catch (KeyboardInputException e) {
                    System.out.println("ERRO DE ENTRADA: " + e.getMessage());
                } catch (AcademicSecurityException e) {
                    System.out.println("ERRO DE SEGURANCA: " + e.getMessage());
                } catch (AcademicSystemException e) {
                    System.out.println("ERRO: " + e.getMessage());
                }
            }
        }
        System.out.println("Sistema encerrado.");
    }
}
