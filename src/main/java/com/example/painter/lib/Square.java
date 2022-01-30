package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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
    public String toFileString() {

        return String.format(Locale.ENGLISH,"%s %.1f %.1f %.1f %s %s");
    }

    @Override
    public String toString()
    {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("###.##", otherSymbols);
        String nameOfColor=color.toString();
        String info="Square ";
        info+=String.format(Locale.ENGLISH,"[%.1f, %.1f] (%.1f) %s %s",point.getX(),point.getY(),size,(isFill ? "fill":"stroke"),nameOfColor);
        return info;
    }
}
