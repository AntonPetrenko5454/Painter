package com.example.painter;

import com.example.painter.lib.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MainController {
    private ObservableList<Shape> shapes;
    private Shape currentShape;
    private boolean isDrawing;
    @FXML
    private TextField numOfSidesTextField;
    @FXML
    private RadioButton polygonRadioButton;
    @FXML
    private Label welcomeText;
    @FXML
    private Label errorLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private Button drawButton;
    @FXML
    private Label numOfSidesLabel;
    @FXML
    private TextField sizeTextField;
    @FXML
    private CheckBox isFillCheckBox;
    @FXML
    private TextField size2TextField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField size3TextField;
    @FXML
    private RadioButton triangleRadioButton;
    @FXML
    private RadioButton circleRadioButton;
    @FXML
    private ListView<Shape> shapesListView;
    @FXML
    private RadioButton squareRadioButton;

    @FXML
    void onCanvasMouseMoved(MouseEvent event) {
        if (isDrawing) {
            currentShape.moveTo(new Point2D(event.getX(), event.getY()));
            redraw();
            currentShape.draw(canvas);
        }
    }

    @FXML
    void onCanvasMousePressed(MouseEvent event) {
        int size = 0;

        if (isDrawing) {
            size = Integer.parseInt(sizeTextField.getText());
            shapes.add(currentShape);
            isDrawing = false;
        }

    }


    void redraw() {

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(canvas);

        }
    }

    // TODO: Сделать ToString у всех фигур, чтобы в ListView
    //       отображалась основная иноформация о фигуре: название, координаты, размеры, цвет, заливка.

    @FXML
    public void initialize() {
        shapes = FXCollections.observableArrayList();
        shapesListView.setItems(shapes);
        isDrawing = false;
        ToggleGroup radioGroup = new ToggleGroup();
        squareRadioButton.setToggleGroup(radioGroup);
        triangleRadioButton.setToggleGroup(radioGroup);
        circleRadioButton.setToggleGroup(radioGroup);
        polygonRadioButton.setToggleGroup(radioGroup);
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == triangleRadioButton && newValue.isSelected()) {
                    size2TextField.setVisible(true);
                    size3TextField.setVisible(true);
                } else {
                    size2TextField.setVisible(false);
                    size3TextField.setVisible(false);
                }
                if (newValue == polygonRadioButton && newValue.isSelected()) {
                    numOfSidesLabel.setVisible(true);
                    numOfSidesTextField.setVisible(true);
                } else {
                    numOfSidesLabel.setVisible(false);
                    numOfSidesTextField.setVisible(false);
                }
            }
        });
    }

    @FXML
    void drawButtonCLick(ActionEvent event) {
        Color color = colorPicker.getValue();
        boolean isFill = isFillCheckBox.isSelected();
        int size = 0;
        int size2 = 0;
        int size3 = 0;
        isDrawing = true;
        if (squareRadioButton.isSelected()) {
            size = Integer.parseInt(sizeTextField.getText());
            currentShape = new Square(new Point2D(0, 0), size, color, isFill);
        } else if (circleRadioButton.isSelected()) {
            size = Integer.parseInt(sizeTextField.getText());
            currentShape = new Circle(new Point2D(0, 0), size, color, isFill);
        } else if (triangleRadioButton.isSelected()) {
            size = Integer.parseInt(sizeTextField.getText());
            size2 = Integer.parseInt(sizeTextField.getText());
            size3 = Integer.parseInt(sizeTextField.getText());
            try {
                currentShape = new Triangle(new Point2D(0, 0), size, size2, size3, color, isFill);
            } catch (ArithmeticException exception) {
                errorLabel.setVisible(true);
                errorLabel.setText(exception.getMessage());
                isDrawing = false;
            }
        } else if (polygonRadioButton.isSelected()) {
            size = Integer.parseInt(sizeTextField.getText());
            int numOFSides = Integer.parseInt(numOfSidesTextField.getText());
            currentShape = new Polygon(new Point2D(0, 0), size, numOFSides, color, isFill);
        } else {
            isDrawing = false;
        }
    }
}