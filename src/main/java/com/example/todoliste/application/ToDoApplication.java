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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Pane pane = new Pane();
        VBox input = new VBox();
        TextField tf_task = new TextField();
        Label lbl_task = new Label("TASK");
        Label lbl_date = new Label("DEADLINE");
        TextField tf_date = new TextField();
        Button btn_add = new Button("ADD");
        Button btn_clear = new Button("CLEAR");
        BorderPane borderPane = new BorderPane();
        TableView<Task> tView = new TableView<>();
        TableColumn<Task, String> taskCol = new TableColumn<>("Task");
        TableColumn<Task, String> dateCol = new TableColumn<>("Date");
        TableColumn<Task, IntegerProperty> priorityCol = new TableColumn<>("Priority");
        TableColumn<Task, StringProperty> statusCol = new TableColumn<>("Status");
        ObservableList<String> options = FXCollections.observableArrayList("Done", "In progress", "Backlog");
        ObservableList<Integer> priorities = FXCollections.observableArrayList();
        final ObservableList<Task> todo = FXCollections.observableArrayList();

        AtomicInteger numTasks = new AtomicInteger(0);

        pane.getChildren().addAll(input, borderPane);

        input.getChildren().addAll(lbl_task, tf_task, lbl_date, tf_date, btn_add, btn_clear);
        borderPane.setLeft(input);
        borderPane.setCenter(tView);

        btn_add.setPrefWidth(100);
        btn_clear.setPrefWidth(100);
        taskCol.setPrefWidth(200);
        dateCol.setPrefWidth(200);
        statusCol.setPrefWidth(200);
        priorityCol.setPrefWidth(200);

        tView.setEditable(true);
        tView.getColumns().addAll(taskCol, dateCol, priorityCol, statusCol);


        btn_add.setOnAction(e ->{
            String task = tf_task.getText();
            String date = tf_date.getText();
            Task newTask = new Task(task, date);
            //ta_date.setPadding(new Insets(20, 10, 20, 10));
            todo.add(newTask);
            taskCol.setCellValueFactory((p) -> p.getValue().taskProperty()); //return
            dateCol.setCellValueFactory((p) -> p.getValue().dateProperty()); //return

            statusCol.setCellFactory((p)->{
                TableCell<Task, StringProperty> status = new TableCell<>();
                final ComboBox<String> combo_status = new ComboBox<>(options);
                combo_status.prefWidthProperty().bind(statusCol.widthProperty().subtract(2));
                status.graphicProperty().bind(Bindings.when(status.emptyProperty()).then((Node) null).otherwise(combo_status));
                return status;
            });

            numTasks.incrementAndGet();
            priorityCol.setCellFactory((p) ->{
                TableCell<Task, IntegerProperty> priority = new TableCell<>();
                final ComboBox<Integer> combo_priority = new ComboBox<>(priorities);
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
            tf_date.clear();
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