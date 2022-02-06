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
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainController {
    private Shape selectedShape=null;
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
    private Slider scaleSlider;
    @FXML
    private MenuItem loadMenuItem;
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
    void deleteButtonClick(ActionEvent event) {
        Shape shape =shapesListView.getSelectionModel().getSelectedItem();
        shapes.remove(shape);
        redraw();
    }

    public void onShapesListViewClick(MouseEvent event)
    {
        Shape shape =shapesListView.getSelectionModel().getSelectedItem();
        double scale;
        if(shape!=null)
        {
            if (selectedShape!=null)
            {
                selectedShape.setSelected(false);
            }

            selectedShape=shape;
            selectedShape.setSelected(true);


            scale=shape.getScale();
            scaleSlider.setValue(scale*100);

            redraw();
        }

    }


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

    @FXML
    void saveMenuItemClick() throws IOException {
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showSaveDialog(null);
        if (file != null)
        {
            Path path= Paths.get(file.toURI());
            BufferedWriter writer= Files.newBufferedWriter(path);
            for (int i=0;i<shapes.size();i++)
            {
                writer.write(shapes.get(i).toFileString()+"\n");
            }
            writer.close();
        }
    }
    @FXML
    void loadMenuItemClick() throws  IOException{
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(null);
        if (file !=null)
        {
            Scanner input=new Scanner(file);
            while (input.hasNextLine())
            {
                String line ;
                String[] allInfo;

                line=input.nextLine();
                allInfo=line.split("\\s");
                switch (allInfo[0])
                {
                    case "Circle":
                        Circle circle=new Circle(new Point2D(Double.parseDouble(allInfo[1]),Double.parseDouble(allInfo[2])),
                                Double.parseDouble(allInfo[3]),Color.web(allInfo[5]),(allInfo[4].compareTo("fill")==0?true:false));
                        shapes.add(circle);

                        break;
                    case "Polygon":
                        Polygon polygon=new Polygon(new Point2D(Double.parseDouble(allInfo[1]),Double.parseDouble(allInfo[2])),
                                Double.parseDouble(allInfo[3]),Integer.parseInt(allInfo[4]),Color.web(allInfo[6]),(allInfo[5].compareTo("fill")==0?true:false));
                        shapes.add(polygon);
                        break;
                    case "Square":
                        Square square=new Square(new Point2D(Double.parseDouble(allInfo[1]),Double.parseDouble(allInfo[2])),Double.parseDouble(allInfo[3]),
                                Color.web(allInfo[5]),(allInfo[4].compareTo("fill")==0?true:false));
                        shapes.add(square);
                        break;
                    case "Triangle":
                        Triangle triangle=new Triangle(new Point2D(Double.parseDouble(allInfo[1]),Double.parseDouble(allInfo[2])),Double.parseDouble(allInfo[3]),
                                Double.parseDouble(allInfo[4]),Double.parseDouble(allInfo[5]),Color.web(allInfo[7]),(allInfo[6].compareTo("fill")==0?true:false));
                        shapes.add(triangle);
                        break;
                }

            }

        }
        redraw();
    }
    void redraw() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(canvas);

        }
    }



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
        scaleSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Shape shape =shapesListView.getSelectionModel().getSelectedItem();
                double scale=newValue.intValue()/100.0;
                if (shape!=null)
                {
                    shape.setScale(scale);
                    redraw();
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