package pl.stqa.pft.sandbox;

public class MyFirstProgram {

 public static void main(String[] args) {
  hello("world");
  hello("user");
  hello("Kasia");

  Square s = new Square(5);
  s.l = 5;
  System.out.println("powierzchnia kwadratu o boku " + s.l + " = " + s.area());

  Rectangle r = new Rectangle(4, 6);
  r.a = 4;
  r.b = 6;
  System.out.println("powierzchnia prostokąta o bokach " + r.a + " i " + r.b + " = " + r.area());

 }

 public static void hello(String somebody) {
  System.out.println("Hello, " + somebody +  "!");
 }


}