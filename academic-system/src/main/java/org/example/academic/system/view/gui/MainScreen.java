package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.Role;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.Session;
import org.example.academic.system.security.SystemOperation;

import java.nio.file.Path;

/**
 * Tela principal JavaFX baseada em papel (TUS-2408). O menu lateral e
 * montado a partir da mesma matriz de permissoes do RBAC: a
 * visibilidade reflete o papel, mas nao substitui a autorizacao, que
 * permanece ativa no controller (AC4).
 */
public class MainScreen {

    private final AcademicSystemController controller;
    private final Session session;
    private final Runnable onLogout;
    private final BorderPane root = new BorderPane();

    public MainScreen(AcademicSystemController controller, Session session, Runnable onLogout) {
        this.controller = controller;
        this.session = session;
        this.onLogout = onLogout;
    }

    public Parent getView() {
        Role role = session.getUser().getRole();

        Label userLabel = new Label("Usuario: " + session.getUser().getUsername()
                + " (" + role.getLabel() + ")");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> onLogout.run());
        HBox top = new HBox(10, userLabel, spacer, logoutButton);
        top.setPadding(new Insets(10));
        top.setAlignment(Pos.CENTER_LEFT);
        top.setStyle("-fx-border-color: lightgray; -fx-border-width: 0 0 1 0;");
        root.setTop(top);

        VBox menu = new VBox(8);
        menu.setPadding(new Insets(10));
        menu.setPrefWidth(240);

        if (AuthorizationService.isAllowed(role, SystemOperation.REGISTER_CLASS)) {
            menu.getChildren().add(menuButton("Cadastrar turma",
                    () -> root.setCenter(new ClassRegistrationScreen(controller).getView())));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.REGISTER_ASSESSMENT)) {
            menu.getChildren().add(menuButton("Cadastrar avaliacao",
                    () -> root.setCenter(new AssessmentRegistrationScreen(controller).getView())));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.VIEW_ACADEMIC_DATA)) {
            menu.getChildren().add(menuButton("Visualizar turmas e avaliacoes",
                    () -> root.setCenter(new VisualizationScreen(controller).getView())));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.SAVE_DATA)) {
            menu.getChildren().add(menuButton("Salvar dados academicos", this::saveData));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.CONFIGURE_PERSISTENCE)) {
            menu.getChildren().add(menuButton("Configurar persistencia",
                    () -> root.setCenter(new PersistenceConfigScreen(controller).getView())));
        }
        menu.getChildren().add(menuButton("Relatorios",
                () -> root.setCenter(new ReportScreen(controller).getView())));

        root.setLeft(menu);

        Label welcome = new Label("Selecione uma operacao no menu lateral.");
        BorderPane.setAlignment(welcome, Pos.CENTER);
        root.setCenter(welcome);
        return root;
    }

    private Button menuButton(String label, Runnable action) {
        Button button = new Button(label);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(event -> action.run());
        return button;
    }

    private void saveData() {
        try {
            Path target = controller.saveAcademicData();
            Dialogs.info("Dados salvos com sucesso em:\n" + target
                    + "\n(formato " + controller.getCurrentPersistenceType() + ")");
        } catch (AcademicSystemException | AcademicSecurityException e) {
            Dialogs.error(e.getMessage());
        }
    }
}
