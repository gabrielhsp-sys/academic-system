package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;

/**
 * Tela JavaFX de cadastro de turmas (TUS-2409). O cadastro e delegado
 * ao AcademicSystemController (AC4); erros de validacao do dominio sao
 * exibidos ao administrador (AC3).
 */
public class ClassRegistrationScreen {

    private final AcademicSystemController controller;

    public ClassRegistrationScreen(AcademicSystemController controller) {
        this.controller = controller;
    }

    public Parent getView() {
        Label header = new Label("Cadastro de turma");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField codeField = new TextField();
        codeField.setPromptText("Ex.: CC101");
        TextField titleField = new TextField();
        titleField.setPromptText("Ex.: Orientacao a Objetos");

        Button registerButton = new Button("Cadastrar");
        registerButton.setDefaultButton(true);
        registerButton.setOnAction(event -> {
            try {
                AcademicClass academicClass =
                        controller.registerClass(codeField.getText(), titleField.getText());
                Dialogs.info("Turma cadastrada com sucesso: "
                        + academicClass.getCode() + " - " + academicClass.getTitle());
                codeField.clear();
                titleField.clear();
            } catch (AcademicSystemException | AcademicSecurityException e) {
                Dialogs.error(e.getMessage());
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(header, 0, 0, 2, 1);
        grid.add(new Label("Codigo:"), 0, 1);
        grid.add(codeField, 1, 1);
        grid.add(new Label("Titulo:"), 0, 2);
        grid.add(titleField, 1, 2);
        grid.add(registerButton, 1, 3);
        return grid;
    }
}
