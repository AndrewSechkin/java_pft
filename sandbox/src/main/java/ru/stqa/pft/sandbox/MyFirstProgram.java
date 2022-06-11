package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Andrey");
    hello("user");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со строной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(0,6);
    Point p2 = new Point(5,10); 
    System.out.println("Расстояние между точками" + " = " + p1.distance(p2));
    System.out.println("Расстояние между точками" + " = " + p2.distance(p1));
  }
public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }
  }

