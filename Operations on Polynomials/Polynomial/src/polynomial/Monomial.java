package polynomial;

public class Monomial {
  private double coef;
  private int exp;

  public Monomial(double coef, int exp) {
    this.coef = coef;
    this.exp = exp;
  }

  public Monomial addMono(Monomial mon2) {
    return new Monomial(this.getCoef() + mon2.getCoef(), this.getExp());
  }

  public String printC() {
    return Integer.toString((int) this.getCoef());
  }

  public String printE() {
    return Integer.toString(this.getExp());
  }

  public double getCoef() {
    return coef;
  }

  public void setCoef(double coef) {
    this.coef = coef;
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }
}
