package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.AcademicSecurityException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

/**
 * Tela JavaFX de visualizacao de turmas e avaliacoes (TUS-2413),
 * exibindo codigo, titulo, tipo, valor e peso em uma arvore. Os dados
 * sao obtidos exclusivamente atraves do controller (AC5).
 */
public class VisualizationScreen {

    private final AcademicSystemController controller;
    private final TreeView<String> tree = new TreeView<>();

    public VisualizationScreen(AcademicSystemController controller) {
        this.controller = controller;
    }

    public Parent getView() {
        Button refreshButton = new Button("Atualizar");
        refreshButton.setOnAction(event -> rebuild());

        HBox top = new HBox(refreshButton);
        top.setPadding(new Insets(10));

        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setCenter(tree);
        BorderPane.setMargin(tree, new Insets(0, 10, 10, 10));

        rebuild();
        return pane;
    }

    private void rebuild() {
        try {
            TreeItem<String> root = new TreeItem<>("Turmas cadastradas");
            root.setExpanded(true);
            for (AcademicClass academicClass : controller.listClasses()) {
                TreeItem<String> classItem = new TreeItem<>(
                        academicClass.getCode() + " - " + academicClass.getTitle());
                classItem.setExpanded(true);
                for (Assessment assessment : academicClass.getAssessments()) {
                    classItem.getChildren().add(new TreeItem<>(
                            "[" + assessment.getType().getLabel() + "] "
                                    + assessment.getDescription()
                                    + " | Valor: " + assessment.getValue()
                                    + " | Peso: " + assessment.getWeight()));
                }
                root.getChildren().add(classItem);
            }
            tree.setRoot(root);
        } catch (AcademicSecurityException e) {
            Dialogs.error(e.getMessage());
        }
    }
}
