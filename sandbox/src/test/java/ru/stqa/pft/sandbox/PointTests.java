package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceFirst() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(9, 7);
    Assert.assertEquals(p1.distance(p2), 9.433981132056603, 1);
  }

  @Test
  public void testDistanceSecond() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0, 0);
  }

  @Test
  public void testDistanceThird() {
    Point p1 = new Point(-9, -1);
    Point p2 = new Point(-5, -3);
    Assert.assertEquals(p1.distance(p2), 4.47213595499958, 4.47213595499958);
  }
}