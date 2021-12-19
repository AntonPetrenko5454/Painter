package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape
{
    private double[] sides=new double[3];
    public Triangle(Point2D point, double side1,double side2,double side3,  Color color)
    {
        super(point,color);
        sides[0]=side1;
        sides[1]=side2;
        sides[2]=side3;

        if (side1+side2<side3 || side1+side3<side2 || side2+side3<side1)
        {
            throw new ArithmeticException("Треугольника с данными сторонами не существует");
        }

    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

    }
}
