package com.meuapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;

    @Override
    public void start(Stage stage) throws IOException {
        // Inicializa a Scene com a tela principal
        scene = new Scene(loadFXML("primary"), SCENE_WIDTH, SCENE_HEIGHT);

        // Carregamento de CSS via código para contornar o erro de FXML
        String cssPath = getClass().getResource("style.css").toExternalForm();
        if (cssPath != null) {
            scene.getStylesheets().add(cssPath);
        }

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Permite que os Controladores troquem o nó raiz da Scene.
     * 
     * @param fxml O nome do arquivo FXML (ex: "secondary")
     */
    public static void setRoot(String fxml) throws IOException { // Alterado para public
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}