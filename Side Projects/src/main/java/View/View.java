package View;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class View extends Application  {
    @Override
    public void start(Stage stage)throws Exception{
        URL url = new File("src/main/java/WeatherApp.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root,300,275) ;
        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.setHeight(330);
        stage.setWidth(290);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String args[]){
        launch(args);
    }
}
