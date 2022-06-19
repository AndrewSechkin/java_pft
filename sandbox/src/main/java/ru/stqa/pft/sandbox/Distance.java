package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {
    Point p1 = new Point(4, 15);
    Point p2 = new Point(4, 1);


    System.out.println("Расстояние между двумя точками p1 и p2 равно " + p1.distance(p2));
  }
}