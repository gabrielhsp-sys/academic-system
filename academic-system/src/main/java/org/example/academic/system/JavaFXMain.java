package org.example.academic.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.controller.AuthenticationController;
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
import org.example.academic.system.view.gui.LoginScreen;
import org.example.academic.system.view.gui.MainScreen;

/**
 * Ponto de entrada da aplicacao JavaFX (TUS-2406). Reutiliza os mesmos
 * controllers e services da aplicacao de linha de comando (AC5) e
 * autentica exclusivamente atraves do AuthenticationController
 * (TUS-2414), sem depender do AuthenticationService.
 */
public class JavaFXMain extends Application {

    private AuthenticationController authenticationController;
    private AuthorizationService authorizationService;
    private ClassService classService;
    private AssessmentService assessmentService;
    private PersistenceService persistenceService;
    private ReportService reportService;

    private Stage stage;
    private Session session;

    @Override
    public void init() {
        AcademicSystem academicSystem = AcademicSystem.getInstance();
        DomainValidator validator = new DomainValidator();
        authenticationController = new AuthenticationController(
                new AuthenticationService(new TxtUserRepository()));
        authorizationService = new AuthorizationService();
        classService = new ClassService(academicSystem, validator);
        assessmentService = new AssessmentService(academicSystem, validator);
        persistenceService = new PersistenceService(academicSystem);
        reportService = new ReportService(academicSystem, persistenceService);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Academic System");
        showLoginScreen();
        stage.show();
    }

    private void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(authenticationController, this::onLoginSuccess);
        stage.setScene(new Scene(loginScreen.getView(), 420, 280));
    }

    private void onLoginSuccess(User user) {
        session = new Session(user);
        AcademicSystemController controller = new AcademicSystemController(
                session, classService, assessmentService,
                persistenceService, reportService, authorizationService);
        MainScreen mainScreen = new MainScreen(controller, session, this::logout);
        stage.setScene(new Scene(mainScreen.getView(), 980, 620));
    }

    private void logout() {
        authenticationController.logout(session);
        session = null;
        showLoginScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
