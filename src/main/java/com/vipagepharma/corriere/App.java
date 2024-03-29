package com.vipagepharma.corriere;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    public static Stage stage_APP;
    private static Scene scene;

    public static Stage popup_stage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("autenticazione/login/SchermataLogin"), 1280, 800);
        scene.getRoot().setStyle("-fx-font-family: 'Arial'");
        stage.setScene(scene);
        stage_APP = stage;
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getRoot().setStyle("-fx-font-family: 'Arial'");
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void newWind(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        popup_stage = new Stage();
        Scene newscene = new Scene(root1, 720,480);
        newscene.getRoot().setStyle("-fx-font-family: 'Arial'");
        popup_stage.setScene(newscene);
        popup_stage.initModality(Modality.WINDOW_MODAL);
        popup_stage.initOwner(stage_APP);
        //popup_stage.initStyle(StageStyle.UNDECORATED);
        popup_stage.show();
    }

    public static void newWind(String fxml, ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        popup_stage = new Stage();
        Scene newscene = new Scene(root1, 720,480);
        newscene.getRoot().setStyle("-fx-font-family: 'Arial'");
        popup_stage.setScene(newscene);
        popup_stage.initModality(Modality.WINDOW_MODAL);
        popup_stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        //popup_stage.initStyle(StageStyle.UNDECORATED);
        popup_stage.show();
    }


    public static void newWind(String fxml, MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        popup_stage = new Stage();
        Scene newscene = new Scene(root1, 720,480);
        newscene.getRoot().setStyle("-fx-font-family: 'Arial'");
        popup_stage.setScene(newscene);
        popup_stage.initModality(Modality.WINDOW_MODAL);
        popup_stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        //popup_stage.initStyle(StageStyle.UNDECORATED);
        popup_stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}