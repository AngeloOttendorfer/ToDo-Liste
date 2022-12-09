package com.example.todoliste;

import com.example.todoliste.model.Task;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
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

public class HelloApplication extends Application {
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
        TableColumn<Task, String> priorityCol = new TableColumn<>("Priority");
        TableColumn<Task, StringProperty> statusCol = new TableColumn<>("Status");
        ObservableList<String> options = FXCollections.observableArrayList("Done", "In progress", "Backlog");

        final ObservableList<Task> todo = FXCollections.observableArrayList();

        pane.getChildren().addAll(input, borderPane);

        input.getChildren().addAll(lbl_task, tf_task, lbl_date, tf_date, btn_add, btn_clear);
        borderPane.setLeft(input);
        borderPane.setCenter(tView);

        btn_add.setPrefWidth(100);
        btn_clear.setPrefWidth(100);

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
                TableCell<Task, StringProperty> c = new TableCell<>();
                final ComboBox<String> comboBox = new ComboBox<>(options);
                c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
                return c;
            });
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