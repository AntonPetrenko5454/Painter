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
    public Shape(Point2D point, Color color)
    {
        this.point=point;
        this.color=color;

    }
    public abstract void draw(Canvas canvas);
    public void moveTo(Point2D point)
    {
        this.point=point;
    }

}
