package com.meuapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary"), SCENE_WIDTH, SCENE_HEIGHT);

        // Carregar CSS com verificação segura
        var cssUrl = App.class.getResource("/com/meuapp/style.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("⚠ Arquivo style.css NÃO encontrado!");
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/meuapp/" + fxml + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
