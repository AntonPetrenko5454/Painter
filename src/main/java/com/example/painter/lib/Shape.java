package com.example.painter.lib;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

// Абстрактый класс
// Особенности:
// 1. Невозможно создать экземпляр абстрактного класса
// 2. Абстрактные методы могут не иметь реализации, но должны быть реализованны в классе наследнике
public abstract class Shape
{
    protected Point2D point;
    protected double scale;
    protected Color color;
    protected boolean isFill;
    protected boolean isSelected;
    public Shape(Point2D point, Color color,boolean isFill)
    {
        this.scale=1;
        this.point=point;
        this.color=color;
        this.isFill=isFill;
        this.isSelected=false;
    }
    public void setScale(double scale)
    {
        this.scale=scale;
    }
    public abstract void draw(Canvas canvas);

    public double getScale()
    {
        return scale;
    }
    public void setSelected(boolean isSelected)
    {
        this.isSelected=isSelected;
    }

    public void moveTo(Point2D point)
    {
        this.point=point;
    }
    public abstract String toFileString();
}
