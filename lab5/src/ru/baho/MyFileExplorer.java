package ru.baho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class MyFileExplorer {
class FileNode {
  String name;
  boolean isDir;

  @Override
  public String toString()
  {
    if (isDir)
    {
      return "dir:\t" + name;
    }
    else
    {
      return name;
    }
  }
}
  Scene sceneMain = null;
  private File curPath = null;
  private Stage wereStage;

  public FileNode[] getNodes(File file)
  {
    if (file != null)
    {
      curPath = file;
      return getNodes();
    }
    curPath = null;
    File[] curFiles = File.listRoots();
    FileNode[] fileNodes = new FileNode[curFiles.length];
    for (int i = 0; i < curFiles.length; i++)
    {
      fileNodes[i] = new FileNode();
      fileNodes[i].name = curFiles[i].getAbsolutePath();
      fileNodes[i].isDir = true;
    }
    return fileNodes;
  }

  public FileNode[] getNodes()
  {
    String[] curFiles = curPath.list();
    FileNode[] fileNodes = new FileNode[curFiles.length];
    for (int i = 0; i < curFiles.length; i++)
    {
      fileNodes[i] = new FileNode();
      fileNodes[i].name = curFiles[i];
      fileNodes[i].isDir = (new File(curPath + File.separator + curFiles[i])).isDirectory();
    }
    return fileNodes;
  }

  private ObservableList< FileNode > sort(ObservableList< FileNode > nodes)
  {
    nodes.sort((o1, o2) ->
    {
      if (o1.isDir)
      {
        if (!o2.isDir)
        {
          return -1;
        }
      }
      else {
        if (o2.isDir)
        {
          return 1;
        }
      }
      return o1.name.compareTo(o2.name);
    });
    return nodes;
  }
  ObservableList< FileNode > fileNodes;

  public void start(Stage stage)
  {
    wereStage = stage;
    stage.setTitle("MyFileExplorer");
    Button backButton = new Button("back");
    Button editButton = new Button("edit");
    Button createFileButton = new Button("createButton");
    Button deleteFileButton = new Button("deleteButton");
    fileNodes = FXCollections.observableArrayList(getNodes(null));
    ListView<FileNode> listView = new ListView<FileNode>(fileNodes);
    backButton.setOnMouseClicked(event ->
    {
      if (curPath != null)
      {
        fileNodes.setAll(getNodes(curPath.getParentFile()));
        sort(fileNodes);
        listView.refresh();
      }
    });
    editButton.setOnMouseClicked(event -> {
      if (listView.getSelectionModel().getSelectedItem() != null && !listView.getSelectionModel().getSelectedItem().isDir)
      {
        if (curPath != null)
        {
          (new PropetiesEditor()).start(wereStage, new File(curPath + File.separator + listView.getSelectionModel().getSelectedItem().name));
        }
      }
    });
    createFileButton.setOnMouseClicked(event -> {
      if (curPath != null)
      {
        TextInputDialog textInputDialog = new TextInputDialog("new_file");
        textInputDialog.setTitle("create file dialog");
        textInputDialog.setHeaderText("введите имя создаваемого файла");
        textInputDialog.setContentText("Name:");
        Optional<String> result = textInputDialog.showAndWait();

        result.ifPresent(name -> {
          File tmp = new File(curPath.getAbsolutePath() + File.separator + name);
          boolean ok = true;
          try
          {
            if (!tmp.exists())
            {
              if (!tmp.createNewFile()) ok = false;
            }
            else
            {
              ok = false;
            }
          }
          catch (IOException ex)
          {
            ok = false;
          }
          if (ok)
          {
            textInputDialog.close();
            fileNodes.setAll(getNodes());
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("error, can't create file");
            alert.show();
          }
        });
      }
    });
    deleteFileButton.setOnMouseClicked(event -> {
      if (curPath != null)
      {
        boolean ok = true;
        try
        {
          File tmp = new File(curPath + File.separator + listView.getSelectionModel().getSelectedItem().name);
          if (tmp.exists())
          {
            if (!tmp.delete()) ok = false;
          }
          else
          {
            ok = false;
          }
        } catch (Exception ex)
        {
          ok = false;
        }
        if (ok)
        {
          fileNodes.setAll(getNodes());
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setContentText("error, can't delete file");
          alert.show();
        }
      }
    });
    listView.setOnMousePressed(event -> {
      if (listView.getSelectionModel().getSelectedItem() != null && listView.getSelectionModel().getSelectedItem().isDir)
      {
        if (curPath != null)
        {
          fileNodes.setAll(getNodes(
                  new File(curPath.getAbsolutePath() + File.separator + listView.getSelectionModel().getSelectedItem().name)));
        }
        else
        {
          fileNodes.setAll(getNodes(new File(listView.getSelectionModel().getSelectedItem().name)));
        }
        sort(fileNodes);
        listView.refresh();
      }
    });
    FlowPane root = new FlowPane(Orientation.VERTICAL, 10,10, listView, backButton, createFileButton,
            deleteFileButton,editButton);
    stage.setTitle("FileExplorer");

    sceneMain = new Scene(root, 600, 400);
    stage.setScene(sceneMain);
    stage.show();
  }
}