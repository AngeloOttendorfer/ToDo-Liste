package com.example.todoliste.application;

import com.example.todoliste.model.Task;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Pane pane = new Pane();
        HBox input = new HBox();
        TextField tf_task = new TextField();
        TextField tf_day = new TextField();
        TextField tf_time = new TextField();
        TextField tf_date = new TextField();
        TextField tf_info = new TextField();
        Button btn_add = new Button("ADD");
        Button btn_clear = new Button("CLEAR");
        BorderPane borderPane = new BorderPane();
        TableView<Task> tView = new TableView<>();
        TableColumn<Task, String> taskCol = new TableColumn<>("Task");
        TableColumn<Task, String> dayCol = new TableColumn<>("Day");
        TableColumn<Task, String> timeCol = new TableColumn<>("Time");
        TableColumn<Task, String> dateCol = new TableColumn<>("Deadline");
        TableColumn<Task, String> infoCol = new TableColumn<>("Description");
        TableColumn<Task, IntegerProperty> priorityCol = new TableColumn<>("Priority");
        TableColumn<Task, StringProperty> statusCol = new TableColumn<>("Status");
        ObservableList<String> options = FXCollections.observableArrayList("Done", "In progress", "Backlog");
        ObservableList<Integer> priorities = FXCollections.observableArrayList();
        final ObservableList<Task> todo = FXCollections.observableArrayList();

        AtomicInteger numTasks = new AtomicInteger(0);

        pane.getChildren().addAll(input, borderPane);

        input.getChildren().addAll(tf_task, tf_day, tf_time, tf_date, tf_info, btn_add, btn_clear);
        borderPane.setCenter(tView);
        borderPane.setBottom(input);


        //tView.setPrefWidth(900);
        tf_task.setPromptText("Task name");
        tf_day.setPromptText("Expiration day");
        tf_time.setPromptText("Expiration time");
        tf_date.setPromptText("Expiration date");
        tf_info.setPromptText("Add specific descriptions");
        btn_add.setPrefWidth(100);
        btn_clear.setPrefWidth(100);
        taskCol.setPrefWidth(200);
        dayCol.setPrefWidth(200);
        timeCol.setPrefWidth(200);
        dateCol.setPrefWidth(200);
        infoCol.setPrefWidth(200);
        statusCol.setPrefWidth(200);
        priorityCol.setPrefWidth(200);

        tView.setEditable(true);
        tView.getColumns().addAll(taskCol, dayCol, timeCol, dateCol, infoCol, priorityCol, statusCol);
        tView.setOnMouseClicked(event -> tView.getSelectionModel().clearSelection());


        btn_add.setOnAction(e ->{
            String task = tf_task.getText();
            String day = tf_day.getText();
            String time = tf_time.getText();
            String date = tf_date.getText();
            String info = tf_info.getText();
            if (tf_task.getText().isEmpty() || tf_day.getText().isEmpty() || tf_time.getText().isEmpty() || tf_date.getText().isEmpty() || tf_info.getText().isEmpty()) {
                // Zeige eine Meldung an, wenn nicht alle Felder ausgefüllt sind
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("All fields must be filled");
                alert.setContentText("Please fill in all fields before adding a task.");
                alert.showAndWait();
            }
            else{
                Task newTask = new Task(task, day, time, date, info);
                //ta_date.setPadding(new Insets(20, 10, 20, 10));
                todo.add(newTask);
                taskCol.setCellValueFactory((p) -> p.getValue().taskProperty()); //return
                dayCol.setCellValueFactory((p) -> p.getValue().dayProperty()); //return
                timeCol.setCellValueFactory((p) -> p.getValue().timeProperty()); //return
                dateCol.setCellValueFactory((p) -> p.getValue().dateProperty()); //return
                infoCol.setCellValueFactory((p) -> p.getValue().infoProperty()); // return

                statusCol.setCellFactory((p)->{
                    TableCell<Task, StringProperty> status = new TableCell<>();
                    final ComboBox<String> combo_status = new ComboBox<>(options);
                    combo_status.setStyle("-fx-background-color: white");
                    combo_status.prefWidthProperty().bind(statusCol.widthProperty().subtract(2));
                    status.graphicProperty().bind(Bindings.when(status.emptyProperty()).then((Node) null).otherwise(combo_status));
                    return status;
                });

                numTasks.incrementAndGet();
                priorityCol.setCellFactory((p) ->{
                    TableCell<Task, IntegerProperty> priority = new TableCell<>();
                    final ComboBox<Integer> combo_priority = new ComboBox<>(priorities);
                    combo_priority.setStyle("-fx-background-color: white");
                    for (int i = 1; i <= numTasks.get(); i++) {
                        if(!priorities.contains(i)){
                            priorities.add(i);
                        }
                    }
                    combo_priority.prefWidthProperty().bind(statusCol.widthProperty().subtract(2));
                    priority.graphicProperty().bind(Bindings.when(priority.emptyProperty()).then((Node) null).otherwise(combo_priority));
                    return priority;
                });
                tView.setItems(todo);
                tView.getSelectionModel().select(newTask);
                tf_task.clear();
                tf_day.clear();
                tf_time.clear();
                tf_date.clear();
                tf_info.clear();
                // Setze den Prompt Text für tf_task und tf_date wieder auf den ursprünglichen Wert
                tf_task.setPromptText("Enter task");
                tf_date.setPromptText("Enter deadline");
            }
        });


        btn_clear.setOnAction(e -> todo.removeAll(tView.getItems()));

        tView.setItems(todo);

        Scene scene = new Scene(pane);
        stage.setTitle("ToDo-List!");
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}