package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public abstract class Shape
{
    protected Point2D point;
    protected Color color;
    public Shape(Point2D point, Color color)
    {
        this.point=point;
        this.color=color;

    }
    public abstract void draw(Canvas canvas);





}
