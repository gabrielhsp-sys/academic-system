package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.repository.PersistenceType;

/**
 * Tela JavaFX de configuracao de persistencia (TUS-2412): permite ao
 * administrador escolher entre TXT, XML e JSON, delegando a alteracao
 * ao controller (AC5).
 */
public class PersistenceConfigScreen {

    private final AcademicSystemController controller;

    public PersistenceConfigScreen(AcademicSystemController controller) {
        this.controller = controller;
    }

    public Parent getView() {
        Label header = new Label("Configuracao de persistencia");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label currentLabel = new Label("Tipo atual: " + controller.getCurrentPersistenceType());

        ComboBox<PersistenceType> typeCombo = new ComboBox<>();
        typeCombo.getItems().setAll(PersistenceType.values());
        typeCombo.setPromptText("Selecione o tipo");

        Button applyButton = new Button("Aplicar");
        applyButton.setDefaultButton(true);
        applyButton.setOnAction(event -> {
            PersistenceType selected = typeCombo.getValue();
            if (selected == null) {
                Dialogs.error("Selecione um tipo de persistencia.");
                return;
            }
            try {
                controller.configurePersistence(selected.name());
                currentLabel.setText("Tipo atual: " + controller.getCurrentPersistenceType());
                Dialogs.info("Tipo de persistencia configurado para " + selected.name() + ".");
            } catch (AcademicSystemException | AcademicSecurityException e) {
                Dialogs.error(e.getMessage());
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(header, 0, 0, 2, 1);
        grid.add(currentLabel, 0, 1, 2, 1);
        grid.add(new Label("Novo tipo:"), 0, 2);
        grid.add(typeCombo, 1, 2);
        grid.add(applyButton, 1, 3);
        return grid;
    }
}
