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
    protected Color color;
    protected boolean isFill;
    public Shape(Point2D point, Color color,boolean isFill)
    {
        this.point=point;
        this.color=color;
        this.isFill=isFill;
    }
    public abstract void draw(Canvas canvas);
    public void moveTo(Point2D point)
    {
        this.point=point;
    }
    public abstract String toFileString();




}
