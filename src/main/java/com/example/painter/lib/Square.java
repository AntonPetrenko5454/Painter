package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape
{
    private double size;
    public Square(Point2D point,double size, Color color) {
        super(point, color);
        this.size=size;
    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(point.getX() , point.getY() , size , size);
    }
}
