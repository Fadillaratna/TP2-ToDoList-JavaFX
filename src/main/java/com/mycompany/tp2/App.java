package com.mycompany.tp2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("To-Do List App");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ListView<Task> todoList = new ListView<>();
        TextField inputField = new TextField();
        Button addButton = new Button("Tambah");

        addButton.setStyle("-fx-background-color: #877deb; -fx-text-fill: white; -fx-font-weight: bold;");

        addButton.setOnAction(e -> {
            String taskName = inputField.getText();
            if (!taskName.isEmpty()) {
                Task task = new Task(taskName);
                todoList.getItems().add(task);
                inputField.clear();
            }
        });

        todoList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Task> call(ListView<Task> listView) {
                return new ListCell<>() {
                    private final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(Task task, boolean empty) {
                        super.updateItem(task, empty);
                        if (empty || task == null) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            checkBox.setText(task.getTaskName());
                            checkBox.setSelected(task.isCompleted());

                            if (task.isCompleted()) {
                                checkBox.setStyle("-fx-text-fill: grey; -fx-font-size: 14px; -fx-strikethrough: true; -fx-mark-color: #877deb;");
                            } else {
                                checkBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px; -fx-strikethrough: false; -fx-mark-color: black;");
                            }

                            checkBox.setOnAction(e -> {
                                task.setCompleted(checkBox.isSelected());
                                if (checkBox.isSelected()) {
                                    checkBox.setStyle("-fx-text-fill: grey; -fx-font-size: 14px; -fx-strikethrough: true; -fx-mark-color: #877deb;");
                                } else {
                                    checkBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px; -fx-strikethrough: false; -fx-mark-color: black;");
                                }
                            });

                            setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5; -fx-border-color: #ccc;");
                            setGraphic(checkBox);
                        }
                    }
                };
            }
        });

        VBox layout = new VBox(10, titleLabel, inputField, addButton, todoList);
        layout.setPadding(new Insets(20));  

        Scene scene = new Scene(layout, 300, 350);

        primaryStage.setTitle("To-Do List App by Fadilla");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    class Task {
        private final String taskName;
        private boolean completed;

        public Task(String taskName) {
            this.taskName = taskName;
            this.completed = false;
        }

        public String getTaskName() {
            return taskName;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
