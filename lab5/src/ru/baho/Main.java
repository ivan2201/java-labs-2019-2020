package ru.baho;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
      MyFileExplorer myFileExplorer = new MyFileExplorer();
      myFileExplorer.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
