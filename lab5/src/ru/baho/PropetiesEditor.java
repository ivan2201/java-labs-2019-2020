package ru.baho;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PropetiesEditor
{
  String currentKey = null;
  TextField value;
  Properties properties;
  Scene wereScene;
  Stage wereStage;
  File editedFile;

  public void start(Stage primaryStage, File file) {
    wereStage = primaryStage;
    editedFile = file;
    TextField key = new TextField();
    wereScene = primaryStage.getScene();
    try {
      properties = new Properties(file);
    }
    catch(IOException ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("error: can't opewn file " + file.getAbsolutePath());
      Optional optional = alert.showAndWait();
      optional.ifPresent(action -> {
        if (wereScene != null)
        {
          wereStage.setScene(wereScene);
        }
        else
        {
          wereStage.close();
        }
      });
    }
    primaryStage.setTitle("properties editor");
    Label insertKey = new Label();
    Label valueDescription = new Label();
    value = new TextField();
    Button saveButton = new Button();
    saveButton.setText("save value");
    Button saveFileButton = new Button();
    saveFileButton.setText("save file");
    Button closeButton = new Button();
    closeButton.setText("close");
    insertKey.setText("insert a key");
    valueDescription.setText("value:");
    key.textProperty().addListener(event -> {
      currentKey = key.getText();
      findValue();
    });
    saveButton.setOnAction(event ->
    {
      if (properties.containsKey(currentKey))
      {
        properties.replace(currentKey, value.getText());
      }
      else
      {
        properties.put(currentKey, value.getText());
      }
    });
    saveFileButton.setOnAction(event -> {
      try {
        properties.saveFile(file);
      }
      catch (IOException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("error: can't save file " + file.getAbsolutePath());
        alert.show();
      }
    });
    closeButton.setOnAction(event -> {
      wereStage.setScene(wereScene);
    });
    FlowPane root = new FlowPane(Orientation.VERTICAL, insertKey, key, valueDescription, value, saveButton, saveFileButton, closeButton);
    primaryStage.setScene(new Scene(root, 600, 500));
    primaryStage.show();
  }

  void findValue()
  {
    if (currentKey != null) {
      value.setText(properties.get(currentKey));
    }
    else
    {
      value.setText("");
    }
  }
}
