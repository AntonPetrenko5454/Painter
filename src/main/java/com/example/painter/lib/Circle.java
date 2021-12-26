package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape
{
    private double radius;
    public Circle(Point2D point, double radius, Color color,boolean isFill)
    {
        super(point, color, isFill);
        this.radius=radius;
    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc =canvas.getGraphicsContext2D();
        if (isFill)
            gc.fillOval(point.getX(), point.getY(), radius,radius);
        else
            gc.strokeOval(point.getX(), point.getY(), radius,radius);
    }
}
