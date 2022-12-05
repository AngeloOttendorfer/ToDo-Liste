package com.example.todoliste;

import com.example.todoliste.model.Task;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SplitPane splitPane = new SplitPane();//two-sided gui
        VBox input = new VBox();
        VBox tasks = new VBox();
        VBox dates = new VBox();
        VBox priorities = new VBox();
        TextField tf_task = new TextField();
        Label lbl_task = new Label("TASK");
        Label lbl_date = new Label("DEADLINE");
        TextField tf_date = new TextField();
        TextArea ta_priority = new TextArea();
        Button btn_add = new Button("ADD");
        Button btn_clear = new Button("CLEAR");
        BorderPane borderPane = new BorderPane();
        TableView<Task> tView = new TableView<Task>();
        TableColumn<Task, String> taskCol = new TableColumn<Task, String>("Task");
        TableColumn<Task, String> dateCol = new TableColumn<Task, String>("Date");


        input.getChildren().addAll(lbl_task, tf_task, lbl_date, tf_date, btn_add, btn_clear);
        splitPane.getItems().addAll(input, tasks, dates, priorities);


        btn_add.setPrefWidth(100);
        btn_clear.setPrefWidth(100);

        tView.setEditable(true);
        tView.getColumns().addAll(taskCol, dateCol);


        btn_add.setOnAction(e ->{
            String task = tf_task.getText();
            String date = tf_date.getText();
            Task newTask = new Task(task, date);
            CheckBox check = new CheckBox(newTask.getTask());
            TextArea ta_date = new TextArea();
            ta_date.setPadding(new Insets(20, 10, 20, 10));
            ta_date.appendText(newTask.getDate());
            tasks.getChildren().add(check);
            dates.getChildren().add(ta_date);
        });

        btn_clear.setOnAction(e ->{
            tasks.getChildren().clear();
        });

        Scene scene = new Scene(splitPane);
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