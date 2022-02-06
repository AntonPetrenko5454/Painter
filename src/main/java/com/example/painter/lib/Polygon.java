package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Polygon extends Shape
{
    private double size;
    private int sideCount;
    public Polygon(Point2D point, double size,int sideCount, Color color,boolean isFill)
    {
        super(point,color,isFill);
        this.size=size;
        this.sideCount=sideCount;

    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc= canvas.getGraphicsContext2D();
        double sSize=size*scale;
        double[] xPoints=new double[sideCount];
        double[] yPoints=new double[sideCount];
        double z=360.0/sideCount;
        double angle=54.0;
        for (int i=0;i<sideCount;i++)
        {
            xPoints[i]=(point.getX()+Math.cos(angle/180.0*Math.PI)*sSize);
            yPoints[i]=(point.getY()+Math.sin(angle/180.0*Math.PI)*sSize);
            angle+=z;

        }
        gc.setStroke(color);
        gc.setFill(color);
        if (isFill)
            gc.fillPolygon(xPoints,yPoints,sideCount);
        else
            gc.strokePolygon(xPoints,yPoints,sideCount);
        if (isSelected)
        {
            for (int i=0;i<sideCount;i++)
            {
                xPoints[i]=(point.getX()+Math.cos(angle/180.0*Math.PI)*(sSize+1));
                yPoints[i]=(point.getY()+Math.sin(angle/180.0*Math.PI)*(sSize+2));
                angle+=z;
            }
            gc.strokePolygon(xPoints,yPoints,sideCount);
        }

    }




    @Override
    public String toFileString() {
        String nameOfColor=color.toString();
        String info="Polygon";
        return String.format(Locale.ENGLISH,"%s %.1f %.1f %.1f %d %s %s",info,point.getX(),point.getY(),size,sideCount,(isFill ? "fill":"stroke"),nameOfColor);
    }



    @Override
    public String toString()
    {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("###.##", otherSymbols);
        String nameOfColor=color.toString();
        String info="Polygon ";
        info+=String.format(Locale.ENGLISH,"[%.1f, %.1f] (%.1f, %d) %s %s",point.getX(),point.getY(),size,sideCount,(isFill ? "fill":"stroke"),nameOfColor);
        return info;
    }
}
