package ru.baho;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main extends Application
{
  class FileNode
  {
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

  public static void main(String[] args)
  {
    Main.launch();
  }

  private ObservableList< FileNode > sort(ObservableList< FileNode > nodes)
  {
    nodes.sort(new Comparator<FileNode>()
    {
      @Override
      public int compare(FileNode o1, FileNode o2)
      {
        if (o1.isDir)
        {
          if (!o2.isDir)
          {
            return -1;
          }
        }
        else
        {
          if (o2.isDir)
          {
            return 1;
          }
        }
        return o1.name.compareTo(o2.name);
      }
    });
    return nodes;
  }

  @Override
  public void start(Stage stage)
  {
    wereStage = stage;
    stage.setTitle("MyFileExplorer");
    Button backButton = new Button("back");
    Button editButton = new Button("edit");
    Button createFileButton = new Button("createButton");
    Button deleteFileButton = new Button("deleteButton");
    ObservableList< FileNode > fileNodes = FXCollections.observableArrayList(getNodes(null));
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
    editButton.setOnMouseClicked(event ->
    {
      if (curPath != null)
      {
        openEditor(stage, new File(curPath.getAbsolutePath() + File.separator + listView.getSelectionModel().getSelectedItem().name));
      }
      else
      {
        openEditor(stage, new File(listView.getSelectionModel().getSelectedItem().name));
      }
    });
    listView.setOnMousePressed(event ->
    {
      if (listView.getSelectionModel().getSelectedItem().isDir)
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
    FlowPane root = new FlowPane(Orientation.VERTICAL, 10,10, listView, backButton, editButton);
    stage.setTitle("FileExplorer");

    sceneMain = new Scene(root, 600, 400);
    stage.setScene(sceneMain);
    stage.show();
  }

  String readTextFile(File file) throws IOException
  {
    ArrayList< char[] > list = new ArrayList<>();
    BufferedReader fr = new BufferedReader(new FileReader(file));
    boolean err = false;
    int i = 0;
    do
    {
      list.add(new char[65536]);
    } while (fr.read(list.get(i)) == 65536);
    fr.close();
    char buffer[] = new char[list.size() * 65536];
    for (int j = 0; j < buffer.length; j++)
    {
      buffer[j] = list.get(j / 65536)[j % 65536];
    }
    return new String(buffer);
  }

  void saveTextFile(File file, String str) throws IOException
  {
    FileWriter fW = new FileWriter(file);
    fW.write(str);
    fW.flush();
    fW.close();
  }

  private boolean wereReadingError = false;
  public void openEditor(Stage stage,File file)
  {
    if (file.exists())
    {
      Label lbl = new Label();
      TextArea textArea = new TextArea();
      try
      {
        textArea.setText(readTextFile(file));
      }
      catch (IOException ex)
      {
        wereReadingError = true;
        textArea.setText(ex.getMessage());
      }
      Button btn = new Button("Save");
      btn.setOnAction(event -> {
        if (wereReadingError)
        {
          wereStage.setScene(sceneMain);
          wereStage.show();
        }
        else
        {
          try
          {
            saveTextFile(file, textArea.getText());
            wereStage.setScene(sceneMain);
            wereStage.show();
          }
          catch (IOException ex)
          {
            lbl.setText("error");
          }
        }
      });
      FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textArea, btn, lbl);
      root.setAlignment(Pos.CENTER);
      Scene scene = new Scene(root, 600, 400);
      stage.setScene(scene);
      stage.setTitle("Editor");
      stage.show();
    }
  }
}
