package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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
        double sRadius=scale*radius;
        gc.setStroke(color);
        gc.setFill(color);
        if (isFill)
            gc.fillOval(point.getX()-(sRadius/2), point.getY()-(sRadius/2), sRadius,sRadius);
        else
            gc.strokeOval(point.getX()-(sRadius/2), point.getY()-(sRadius/2), sRadius,sRadius);
        if (isSelected)
        {
            gc.strokeOval(point.getX()-((sRadius/2)+1), point.getY()-((sRadius/2)+1), sRadius+2,sRadius+2);
        }
    }




    @Override
    public String toFileString() {
        String nameOfColor=color.toString();
        String info="Circle";
        return String.format(Locale.ENGLISH,"%s %.1f %.1f %.1f %s %s",info,point.getX(), point.getY(),radius,(isFill ? "fill":"stroke"),nameOfColor);
    }



    @Override
    public String toString()
    {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("###.##", otherSymbols);
        String nameOfColor=color.toString();
        String info="Circle ";
        info+=String.format(Locale.ENGLISH,"[%.1f, %.1f] (%.1f) %s %s",point.getX(),point.getY(),radius,(isFill ? "fill":"stroke"),nameOfColor);
        return info;
    }

}
