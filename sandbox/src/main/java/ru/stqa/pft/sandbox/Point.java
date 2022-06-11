package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x1, double y1) {
    this.x = x1;
    this.y = y1;
  }

  public double distance(Point p2) {
    return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
  }

  public double pointX() {
    return x;
  }

  public double pointY() {
    return y;
  }

  public String toString() {
    return "Point{" + "x=" + x + ", y=" + y + '}';
  }
}