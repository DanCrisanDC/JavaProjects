package polynomial;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
  private List<Monomial> poly = new ArrayList<Monomial>();
  private int degree;
  
  public Polynomial() {
    this.degree = 0;
  }

  private void setPoly(List<Monomial> poly) {
    this.poly = poly;
  }

  private List<Monomial> getPoly() {
    return poly;
  }

  private List<Monomial> getPoly(int sign) {
    List<Monomial> rezPoly = new ArrayList<Monomial>();
    for (int i = 0; i < this.getPoly().size(); i++) {
      rezPoly.add(i, new Monomial(0.0, i));
    }
    for (Monomial mon1 : this.getPoly()) {
      rezPoly.set(mon1.getExp(), new Monomial(sign * mon1.getCoef(), mon1.getExp()));
    }
    return rezPoly;
  }

  private void setDegree(int degree) {
    this.degree = degree;
  }

  private int getDegree() {
    return degree;
  }
  
  public void processPoly(String s) {
    String s1 = s.replaceAll("-", "+-");
    String s2 = s1.replaceAll(" ", "");
    String[] mon = new String[100];
    mon = s2.split("\\+");
    int j = 0;
    if (mon[0].isEmpty()) {
      j++;
    }
    if (mon[j].contains("^")) {
      this.degree = Integer.parseInt(mon[j].substring(mon[j].indexOf("^") + 1));
    } else {
      if (mon[j].contains("x")) {
        this.degree = 1;
      } else {
        this.degree = 0;
      }
    }
    for (int i = 0; i <= this.degree; i++) {
      this.poly.add(i, new Monomial(0.0, i));
    }
    int exp = 0;
    double coef = 0.0;
    for (int i = 0; i < mon.length; i++) {
      if (mon[i].contains("x")) {
        if (mon[i].contains("^")) {
          exp = Integer.parseInt(mon[i].substring(mon[i].indexOf("^") + 1));
        } else {
          exp = 1;
        }
        if (mon[i].contains("-") && mon[i].indexOf("-") + 1 == mon[i].indexOf("x")) {
          coef = -1.0;
        } else {
          if (mon[i].indexOf("x") == 0) {
            coef = 1;
          } else {
            coef = Double.parseDouble(mon[i].substring(0, mon[i].indexOf("x")));
          }
        }
      } else {
        exp = 0;
        if (!mon[i].isEmpty()) {
          coef = Double.parseDouble(mon[i]);
        } else {
          coef = 0;
        }
      }
      this.poly.set(exp, new Monomial(coef, exp));
    }
  }

  public Polynomial addPoly(Polynomial poly2) {
    List<Monomial> rezPoly = new ArrayList<Monomial>();
    List<Monomial> smallPoly = new ArrayList<Monomial>();
    if (this.getDegree() > poly2.getDegree()) {
      smallPoly.addAll(poly2.getPoly());
      rezPoly.addAll(this.getPoly());
    } else {
      smallPoly.addAll(this.getPoly());
      rezPoly.addAll(poly2.getPoly());
    }
    for (Monomial mon1 : smallPoly) {
      for (Monomial mon2 : rezPoly) {
        if (mon1.getExp() == mon2.getExp()) {
          rezPoly.set(mon1.getExp(), mon1.addMono(mon2));
        }
      }
    }
    Polynomial rez = new Polynomial();
    rez.setPoly(rezPoly);
    return rez;
  }

  public Polynomial subPoly(Polynomial poly2) {
    List<Monomial> rezPoly = new ArrayList<Monomial>();
    List<Monomial> smallPoly = new ArrayList<Monomial>();
    if (this.getDegree() > poly2.getDegree()) {
      smallPoly.addAll(poly2.getPoly(-1));
      rezPoly.addAll(this.getPoly());
    } else {
      smallPoly.addAll(this.getPoly());
      rezPoly.addAll(poly2.getPoly(-1));
    }
    for (Monomial mon1 : smallPoly) {
      for (Monomial mon2 : rezPoly) {
        if (mon1.getExp() == mon2.getExp()) {
          rezPoly.set(mon1.getExp(), mon1.addMono(mon2));
        }
      }
    }
    Polynomial rez = new Polynomial();
    rez.setPoly(rezPoly);
    return rez;
  }

  public Polynomial derivePoly() {
    List<Monomial> rezPoly = new ArrayList<Monomial>();
    for (Monomial mon1 : this.getPoly()) {
      if (mon1.getExp() == 0) {
        rezPoly.add(0, new Monomial(0.0, 0));
      } else {
        rezPoly.add(mon1.getExp()-1, new Monomial(mon1.getExp()*mon1.getCoef(), mon1.getExp()-1));
      }
    }
    Polynomial rez = new Polynomial();
    rez.setPoly(rezPoly);
    return rez;
  }

  public Polynomial mulPoly(Polynomial poly2) {
    List<Monomial> rezPoly = new ArrayList<Monomial>();
    int degPoly = this.getDegree() + poly2.getDegree();
    double coefFinal = 0.0;
    int expFinal = 0;
    for (int i = 0; i <= degPoly; i++) {
      rezPoly.add(i, new Monomial(0.0, i));
    }
    for (Monomial mon1 : this.getPoly()) {
      for (Monomial mon2 : poly2.getPoly()) {
        coefFinal = mon1.getCoef() * mon2.getCoef();
        expFinal = mon1.getExp() + mon2.getExp();
        rezPoly.set(expFinal, new Monomial(rezPoly.get(expFinal).getCoef() + coefFinal, expFinal));
      }
    }
    Polynomial rez = new Polynomial();
    rez.setPoly(rezPoly);
    return rez;
  }

  public Polynomial integratePoly() {
    List<Monomial> rezPoly = new ArrayList<Monomial>();
    for (int i = 0; i <= this.getDegree() + 1; i++) {
      rezPoly.add(new Monomial(0.0, i));
    }
    for (Monomial mon1 : this.getPoly()) {
      rezPoly.set(mon1.getExp()+1, new Monomial(mon1.getCoef()/(mon1.getExp()+1), mon1.getExp()+1));
    }
    Polynomial rez = new Polynomial();
    rez.setPoly(rezPoly);
    rez.setDegree(rez.getDegree());
    return rez;
  }

  public String printP() {
    String output = new String();
    String newOut = new String();
    for (Monomial mp : this.getPoly()) {
      if (mp.getCoef() >= 1.0 || mp.getCoef() <= -1.0) {
    	if(mp.getCoef() >= 0.0) {
          output = output.concat("+");
        }
        if (mp.getCoef() > -2.0 && mp.getCoef() < 0.0 && mp.getExp() > 0) {
          output = output.concat("-");
        } else {
          if (mp.getCoef() == -1.0 && mp.getExp() == 0) {
            output = output.concat("-1");
          } else {
            if (mp.getCoef() >= 2.0 || mp.getCoef() < 0.0) {
              output = output.concat(mp.printC());
            } else {
            	if (mp.getCoef() >= 1.0 && mp.getExp() == 0)
            		output = output.concat("1");
            }
          }
        }
        if (mp.getExp() != 0) {
          if (mp.getExp() == 1) {
            output = output.concat("x");
          } else {
            output = output.concat("x^");
            output = output.concat(mp.printE());
          }
        }
      }
    }
    if (output.indexOf("+") == 0) {
      newOut = output.substring(output.indexOf("+") + 1);  	
    } else {
        if (output.length() == 0) {
        	return "0";
        } else {
        	newOut = output;
        }
    }
    return newOut;
  }

  public boolean equals(Polynomial p) {
    if (this.getDegree() == p.getDegree()) {
      boolean equalPol = true;
      for (Monomial monom1 : this.getPoly()) {
        for (Monomial monom2 : p.getPoly()) {
          if (monom1.getExp() == monom2.getExp()) {
            if (monom1.getCoef() != monom2.getCoef()) {
              equalPol = false;
            }
          }
        }
      }
      if (equalPol) {
        return true;
      }
    }
    return false;
  }
}
