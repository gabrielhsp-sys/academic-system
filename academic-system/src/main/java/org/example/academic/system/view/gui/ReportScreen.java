package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.Role;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.SystemOperation;

import java.util.function.Supplier;

/**
 * Tela JavaFX de relatorios (TUS-2411). O relatorio de configuracao de
 * persistencia so e exibido para administradores (AC3/AC4); a geracao
 * e delegada ao controller, sem duplicar logica (AC6/AC7).
 */
public class ReportScreen {

    private final AcademicSystemController controller;

    public ReportScreen(AcademicSystemController controller) {
        this.controller = controller;
    }

    public Parent getView() {
        TextArea output = new TextArea();
        output.setEditable(false);
        output.setStyle("-fx-font-family: \"monospace\";");

        Button summaryButton = new Button("Avaliacoes por turma");
        summaryButton.setOnAction(event ->
                showReport(output, controller::generateClassAssessmentSummaryReport));

        Button weightButton = new Button("Pesos das avaliacoes");
        weightButton.setOnAction(event ->
                showReport(output, controller::generateAssessmentWeightReport));

        HBox buttons = new HBox(10, summaryButton, weightButton);
        buttons.setPadding(new Insets(10));

        Role role = controller.getSession().getUser().getRole();
        if (AuthorizationService.isAllowed(role, SystemOperation.GENERATE_PERSISTENCE_REPORT)) {
            Button persistenceButton = new Button("Configuracao de persistencia");
            persistenceButton.setOnAction(event ->
                    showReport(output, controller::generatePersistenceConfigurationReport));
            buttons.getChildren().add(persistenceButton);
        }

        BorderPane pane = new BorderPane();
        pane.setTop(buttons);
        pane.setCenter(output);
        BorderPane.setMargin(output, new Insets(0, 10, 10, 10));
        return pane;
    }

    private void showReport(TextArea output, Supplier<String> reportSupplier) {
        try {
            output.setText(reportSupplier.get());
        } catch (AcademicSystemException | AcademicSecurityException e) {
            Dialogs.error(e.getMessage());
        }
    }
}
