package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape
{
    private double size;
    public Square(Point2D point,double size, Color color,boolean isFill)
    {
        super(point, color,isFill);
        this.size=size;
    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setFill(color);
        if (isFill)
            gc.fillRect(point.getX() , point.getY() , size , size);
        else
            gc.strokeRect(point.getX() , point.getY() , size , size);
    }

    @Override
    public String toString()
    {
        String nameOfColor=color.toString();
        String info="Square ";
        info+=String.format("[%.1f, %.1f] (%.1f) %s %s",point.getX(),point.getY(),size,(isFill ? "fill":"stroke"),nameOfColor);
        return info;
    }
}
