package com.example.todoliste.model;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;

public class PriorityCell extends TableCell<Task, Integer> {
    private ComboBox<Integer> comboBox;

    public PriorityCell() {
        comboBox = new ComboBox<>(FXCollections.observableArrayList());
        comboBox.setMaxWidth(Double.MAX_VALUE);
        comboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                commitEdit(newValue);
            }
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        setGraphic(comboBox);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem() == null ? "" : getItem().toString());
        setGraphic(null);
    }

    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                comboBox.setValue(item);
                setGraphic(comboBox);
            } else {
                setText(item.toString());
                setGraphic(null);
            }
        }
    }
}
