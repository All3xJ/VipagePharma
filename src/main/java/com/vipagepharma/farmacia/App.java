package com.vipagepharma.farmacia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App. piccolo appunto: questa App.java essenzialmente NON devo toccarla credo. gestisco TUTTO dagli altri.
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("autenticazione/login/SchermataLogin"), 640, 480);
        scene = new Scene(loadFXML("autenticazione/login/SchermataLogin"), 1080, 720);
        scene.getRoot().setStyle("-fx-font-family: 'Arial'");
        stage.setScene(scene);
        stage.show();	
    }

	// questo è il metodo che gli altri .java richiamano passando la stringa del nome del fxml che si vuole avere
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getRoot().setStyle("-fx-font-family: 'Arial'");
    }

	// questo è il metodo richiamato dal metodo setRoot di questa stessa classe, letteralmente sopra di questo
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}