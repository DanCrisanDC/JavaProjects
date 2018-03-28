package polynomial;

import static org.junit.Assert.*;
import org.junit.Test;

public class PolynomialTest {

  @Test
  public void testAddPoly() {
    Polynomial p1 = new Polynomial();
    Polynomial p2 = new Polynomial();
    Polynomial r = new Polynomial();
    String s1 = "-3x^2+5";
    String s2 = "-2x-5";
    String s3 = "-2x-3x^2";
    p1.processPoly(s1);
    p2.processPoly(s2);
    r = p1.addPoly(p2);
    String s4 = new String();
    s4 = r.printP();
    assertEquals(s4, s3);
  }

  @Test
  public void testSubPoly() {
    Polynomial p1 = new Polynomial();
    Polynomial p2 = new Polynomial();
    Polynomial r = new Polynomial();
    String s1 = "-3x^2+5";
    String s2 = "-2x-5";
    String s3 = "10+2x-3x^2";
    p1.processPoly(s1);
    p2.processPoly(s2);
    r = p1.subPoly(p2);
    String s4 = new String();
    s4 = r.printP();
    assertEquals(s4, s3);
  }

  @Test
  public void testDerivePoly() {
    Polynomial p1 = new Polynomial();
    Polynomial r = new Polynomial();
    String s1 = "-3x^2+5";
    String s2 = "-6x";
    p1.processPoly(s1);
    r = p1.derivePoly();
    String s3 = new String();
    s3 = r.printP();
    assertEquals(s3, s2);
  }

  @Test
  public void testMulPoly() {
    Polynomial p1 = new Polynomial();
    Polynomial p2 = new Polynomial();
    Polynomial r = new Polynomial();
    String s1 = "-3x^2+5";
    String s2 = "-2x - 5";
    String s3 = "-25-10x+15x^2+6x^3";
    p1.processPoly(s1);
    p2.processPoly(s2);
    r = p1.mulPoly(p2);
    String s4 = new String();
    s4 = r.printP();
    assertEquals(s4, s3);
  }

  @Test
  public void testIntegratePoly() {
    Polynomial p1 = new Polynomial();
    Polynomial r = new Polynomial();
    String s1 = "-3x^2+5";
    String s2 = "5x-x^3";
    p1.processPoly(s1);
    r = p1.integratePoly();
    String s3 = new String();
    s3 = r.printP();
    assertEquals(s3, s2);
  }
}
