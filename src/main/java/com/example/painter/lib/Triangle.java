package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class Triangle extends Shape {
    private double[] sides = new double[3];

    public Triangle(Point2D point, double side1, double side2, double side3, Color color, boolean isFill) {
        super(point, color, isFill);
        sides[0] = side1;
        sides[1] = side2;
        sides[2] = side3;

        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {
            throw new ArithmeticException("Треугольника с данными сторонами не существует");
        }
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setFill(color);
        double[] xPoint = new double[3];
        double[] yPoint = new double[3];
        double[] sSides = new double[3];

        sSides[0] = sides[0] * scale;
        sSides[1] = sides[1] * scale;
        sSides[2] = sides[2] * scale;
        double p = Arrays.stream(sSides).sum() / 2;
        double S = Math.sqrt(p * (p - sSides[0]) * (p - sSides[1]) * (p - sSides[2]));
        double sinA = 2 * S / (sSides[0] * sSides[1]);
        double alpha = Math.asin(sinA);
        double x = sSides[1] * Math.cos(alpha);
        double y = sSides[1] * Math.sin(alpha);
        int ox = (int) (((point.getX() + sSides[0]) + (point.getX()) + (point.getX() + (int) x)) / 3);
        int oy = (int) (((point.getY()) + (point.getY() - (int) y) + (point.getY())) / 3);
        xPoint[0] = point.getX() + sSides[0];
        yPoint[0] = point.getY();
        xPoint[1] = point.getX();
        yPoint[1] = point.getY();
        xPoint[2] = point.getX() + x;
        yPoint[2] = point.getY() - y;
        if (isFill) {
            gc.fillPolygon(xPoint, yPoint, 3);
        } else {
            gc.strokePolygon(xPoint, yPoint, 3);
        }
        if (isSelected)
        {

            gc.strokePolygon(xPoint,yPoint,3);

        }

    }




    @Override
    public String toFileString() {
        String info="Triangle";
        String nameOfColor=color.toString();
        return String.format(Locale.ENGLISH,"%s %.1f %.1f %.1f %.1f %.1f %s %s",info,point.getX(),point.getY(),sides[0],sides[1],sides[2],(isFill ? "fill":"stroke"),nameOfColor);
    }



    @Override
    public String toString()
    {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("###.##", otherSymbols);
        String nameOfColor=color.toString();
        String info="Triangle ";
        info+=String.format(Locale.ENGLISH,"[%.1f, %.1f] (%.1f, %.1f, %.1f) %s %s",point.getX(),point.getY(),sides[0],sides[1],sides[2],(isFill ? "fill":"stroke"),nameOfColor);

        return info;
    }
}
