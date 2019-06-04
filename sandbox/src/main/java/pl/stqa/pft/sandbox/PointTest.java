package pl.stqa.pft.sandbox;

public class PointTest {
  public static void main(String[] args) {

    Point p1 = new Point(1,2);
    Point p2 = new Point(4,4);

    System.out.println("the length of the stretch between points " + p1.x + " , " + p1.y + " and " + p2.x + " , " + p2.y + " = " + p1.distance(p2));
  }
}
