package com.example.painter;

import com.example.painter.lib.Circle;
import com.example.painter.lib.Shape;
import com.example.painter.lib.Square;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<Shape> shapes;
    private Shape currentShape;
    private boolean isDrawing;
    @FXML
    private Label welcomeText;
    @FXML
    private Label errorLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private Button drawButton;
    @FXML
    private TextField sizeTextField;
    @FXML
    private TextField size2TextField;
    @FXML
    private TextField size3TextField;
    @FXML
    private RadioButton triangleRadioButton;
    @FXML
    private RadioButton circleRadioButton;
    @FXML
    private RadioButton squareRadioButton;
    @FXML
    void onCanvasMouseMoved(MouseEvent event)
    {
        if (isDrawing)
        {

            currentShape.moveTo(new Point2D(event.getX(),event.getY()));

            redraw();
            currentShape.draw(canvas);
        }

    }
    @FXML
    void onCanvasMousePressed(MouseEvent event)
    {
        int size=0;

        if (isDrawing)
        {
            size=Integer.parseInt(sizeTextField.getText());
            shapes.add(new Square(new Point2D(event.getX(),event.getY()),size, Color.BLACK));
            isDrawing=false;
        }

    }


    void redraw()
    {

        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        for (Shape shape:shapes)
        {
            shape.draw(canvas);

        }
    }



    @FXML
    public void initialize()
    {
        shapes=new ArrayList<>();
        isDrawing=false;
    }

    @FXML
    void drawButtonCLick(ActionEvent event)
    {
        size2TextField.setVisible(false);
        size3TextField.setVisible(false);


        int size=0;
        int size2=0;
        int size3=0;
        isDrawing=true;

        if (squareRadioButton.isSelected())
        {

            size=Integer.parseInt(sizeTextField.getText());

            currentShape=new Square(new Point2D(0,0),size, Color.BLACK);


        }
        else
        if (circleRadioButton.isSelected())
        {

            size=Integer.parseInt(sizeTextField.getText());
            currentShape=new Circle(new Point2D(0,0),size,Color.RED);

        }
        else
        {
            isDrawing=false;
        }
       /* if (triangleRadioButton.isSelected())
        {
            size2TextField.setVisible(true);
            size3TextField.setVisible(true);
            errorLabel.setVisible(false);
            size=Integer.parseInt(sizeTextField.getText());
            size2=Integer.parseInt(sizeTextField.getText());
            size3=Integer.parseInt(sizeTextField.getText());

            currentShape=new Triangle(new Point2D(0,0),size,size2,size3, Color.BLACK);
            if (size==0 || size2==0||size3==0)
            {
                errorLabel.setVisible(true);
            }

        }
        else
        {

            isDrawing=false;


        }*/


    }

}