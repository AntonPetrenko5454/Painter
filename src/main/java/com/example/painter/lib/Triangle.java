package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Triangle extends Shape
{
    private double[] sides=new double[3];
    public Triangle(Point2D point, double side1, double side2, double side3,  Color color,boolean isFill)
    {
        super(point,color,isFill);
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

        double p = Arrays.stream(sides).sum() / 2;
        double S = Math.sqrt(p * (p - sides[0]) * (p - sides[1]) * (p - sides[2]));
        double sinA = 2 * S / (sides[0] * sides[1]);
        double alpha = Math.asin(sinA);
        double x = sides[1] * Math.cos(alpha);
        double y = sides[1] * Math.sin(alpha);
        int ox = (int) (((point.getX() + sides[0]) + (point.getX()) + (point.getX() + (int)x)) / 3);
        int oy = (int) (((point.getY()) + (point.getY() - (int)y) + (point.getY())) / 3);
        // После расчёта точек, строим линии между ними
        gc.strokeLine(point.getX() + sides[0], point.getY(), point.getX(), point.getY());
        gc.strokeLine(point.getX(), point.getY(), point.getX() + (int)x, point.getY() - (int)y);
        gc.strokeLine(point.getX() + (int)x, point.getY() - (int)y,point.getX() + sides[0], point.getY());
    }
}
