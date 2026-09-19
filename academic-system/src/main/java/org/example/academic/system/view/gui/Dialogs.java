package org.example.academic.system.view.gui;

import javafx.scene.control.Alert;

/** Utilitario de dialogos padrao da interface grafica. */
public final class Dialogs {

    private Dialogs() {
    }

    public static void info(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Academic System");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Academic System");
        alert.setHeaderText("Operacao nao realizada");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
