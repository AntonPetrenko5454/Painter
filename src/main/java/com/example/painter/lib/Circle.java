package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape
{
    private double radius;
    public Circle(Point2D point, double size, Color color)
    {
        super(point, color);
        this.radius=radius;
    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc =canvas.getGraphicsContext2D();
        gc.strokeOval(point.getX(), point.getY(), radius,radius);
    }
}
