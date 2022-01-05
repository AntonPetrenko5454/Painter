package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

        double[] xPoints=new double[sideCount];
        double[] yPoints=new double[sideCount];
        double z=360.0/sideCount;
        double angle=54.0;
        for (int i=0;i<sideCount;i++)
        {
            xPoints[i]=(point.getX()+Math.cos(angle/180.0*Math.PI)*size);
            yPoints[i]=(point.getY()+Math.sin(angle/180.0*Math.PI)*size);
            angle+=z;
        }
        gc.setStroke(color);
        gc.setFill(color);
        if (isFill)
            gc.fillPolygon(xPoints,yPoints,sideCount);
        else
            gc.strokePolygon(xPoints,yPoints,sideCount);
    }
}
