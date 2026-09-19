package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.InvalidNumberInputException;
import org.example.academic.system.exception.KeyboardInputException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.AssessmentType;

/**
 * Tela JavaFX de cadastro de avaliacoes (TUS-2410): selecao de turma
 * existente, tipo, valor e peso, com delegacao ao controller (AC6).
 */
public class AssessmentRegistrationScreen {

    private final AcademicSystemController controller;

    public AssessmentRegistrationScreen(AcademicSystemController controller) {
        this.controller = controller;
    }

    public Parent getView() {
        Label header = new Label("Cadastro de avaliacao");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ComboBox<AcademicClass> classCombo = new ComboBox<>();
        classCombo.setPromptText("Selecione a turma");
        classCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(AcademicClass academicClass) {
                return academicClass == null ? ""
                        : academicClass.getCode() + " - " + academicClass.getTitle();
            }

            @Override
            public AcademicClass fromString(String text) {
                return null;
            }
        });

        Button refreshButton = new Button("Atualizar turmas");
        refreshButton.setOnAction(event -> reloadClasses(classCombo));

        ComboBox<AssessmentType> typeCombo = new ComboBox<>();
        typeCombo.getItems().setAll(AssessmentType.values());
        typeCombo.setPromptText("Tipo de avaliacao");
        typeCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(AssessmentType type) {
                return type == null ? "" : type.getLabel();
            }

            @Override
            public AssessmentType fromString(String text) {
                return null;
            }
        });

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Ex.: Prova 1");
        TextField valueField = new TextField();
        valueField.setPromptText("Ex.: 10.0");
        TextField weightField = new TextField();
        weightField.setPromptText("Ex.: 0.4");

        Button registerButton = new Button("Cadastrar");
        registerButton.setDefaultButton(true);
        registerButton.setOnAction(event -> {
            try {
                AcademicClass selected = classCombo.getValue();
                if (selected == null) {
                    Dialogs.error("Selecione uma turma.");
                    return;
                }
                AssessmentType type = typeCombo.getValue();
                if (type == null) {
                    Dialogs.error("Selecione o tipo de avaliacao.");
                    return;
                }
                double value = parseDouble(valueField.getText());
                double weight = parseDouble(weightField.getText());
                controller.registerAssessment(selected.getCode(), type.name(),
                        descriptionField.getText(), value, weight);
                Dialogs.info("Avaliacao cadastrada com sucesso na turma " + selected.getCode() + ".");
                descriptionField.clear();
                valueField.clear();
                weightField.clear();
            } catch (KeyboardInputException | AcademicSystemException | AcademicSecurityException e) {
                Dialogs.error(e.getMessage());
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(header, 0, 0, 2, 1);
        grid.add(new Label("Turma:"), 0, 1);
        grid.add(classCombo, 1, 1);
        grid.add(refreshButton, 2, 1);
        grid.add(new Label("Tipo:"), 0, 2);
        grid.add(typeCombo, 1, 2);
        grid.add(new Label("Descricao:"), 0, 3);
        grid.add(descriptionField, 1, 3);
        grid.add(new Label("Valor:"), 0, 4);
        grid.add(valueField, 1, 4);
        grid.add(new Label("Peso:"), 0, 5);
        grid.add(weightField, 1, 5);
        grid.add(registerButton, 1, 6);

        reloadClasses(classCombo);
        return grid;
    }

    private void reloadClasses(ComboBox<AcademicClass> classCombo) {
        try {
            classCombo.getItems().setAll(controller.listClasses());
        } catch (AcademicSecurityException e) {
            Dialogs.error(e.getMessage());
        }
    }

    private double parseDouble(String input) {
        try {
            return Double.parseDouble(input.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException(input, e);
        }
    }
}
