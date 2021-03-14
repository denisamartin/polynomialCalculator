package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polynomial {
    private ArrayList<Monomial> monomialList = new ArrayList<>();

    public static Polynomial extractMonomial(String input) {
        Pattern p = Pattern.compile("((-?\\d+(?=x))?(-?[xX])(\\^(-?\\d+))?)|((-?)[xX])|(-?\\d+)");
        Matcher m = p.matcher(input);
        double x = 0;
        int y = 0;
        Polynomial polinom = new Polynomial();
        while (m.find()) {
            if (m.group(3) != null && m.group(2) != null) {
                x = Double.valueOf(m.group(2));
                y = (m.group(5) != null ? Integer.parseInt(m.group(5)) : 1);

            } else {
                x = Integer.parseInt(m.group());
            }
            Monomial monomial = new Monomial(x, y);
            polinom.monomialList.add(monomial);
            x = 0;
            y = 0;
        }
        return polinom;
    }

    public Polynomial addOrSubtractPolynomial(Polynomial a, String op) {
        Polynomial result = new Polynomial();
        Polynomial polinom1=copy(this);
        Polynomial polinom2=copy(a);
        int i = 0, j=0;
        while (i < this.monomialList.size() && j < a.monomialList.size()) {
            Monomial monom1 = this.monomialList.get(i);
            Monomial monom2 = a.monomialList.get(j);
            if (monom1.getExponent() == monom2.getExponent()) {
                polinom1.monomialList.remove(monom1);
                polinom2.monomialList.remove(monom2);
                if (op.equals("add")) {
                    result.monomialList.add(monom1.addMonomial(monom2));
                    i++;
                    j++;
                } else {
                    if (monom1.getCoeficient() == monom2.getCoeficient()) {
                        i++;
                        j++;
                    } else {
                        result.monomialList.add(monom1.subtract(monom2));
                        i++;
                        j++;
                    }
                }
            } else {
                if (monom1.getExponent() > monom2.getExponent()) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        result.monomialList.addAll(polinom1.monomialList);
        result.monomialList.addAll(polinom2.monomialList);
        Collections.sort(result.monomialList);
        return result;
    }


    public Polynomial multiply(Polynomial a) {
        Polynomial result = new Polynomial();
        Polynomial intermediar = new Polynomial();
        for (Monomial monom1: this.monomialList) {
            for (Monomial monom2: a.monomialList) {
                intermediar.monomialList.add(monom1.multiply(monom2));
            }
        }
        for (int i = 0; i < intermediar.monomialList.size(); i++) {
            if (!result.isInList(intermediar.monomialList.get(i))) {
                int ok = 0;
                Monomial rezultatSumaInter= intermediar.monomialList.get(i);
                for (int j = i + 1; j < intermediar.monomialList.size() - 1; j++) {
                    if (intermediar.monomialList.get(i).getExponent() == intermediar.monomialList.get(j).getExponent()){
                        rezultatSumaInter = rezultatSumaInter.addMonomial(intermediar.monomialList.get(j));
                        ok = 1;
                    }
                }
                if (ok == 0) {
                    result.monomialList.add(intermediar.monomialList.get(i));
                } else {
                    result.monomialList.add(rezultatSumaInter);
                }
            }
        }
        return result;
    }


    public ArrayList<Polynomial> dividePolynomials(Polynomial d) {
        ArrayList<Polynomial> rez = new ArrayList<>();
        Polynomial q = new Polynomial();
        Polynomial r = this;
        while ((!r.monomialList.isEmpty()) && (getHighestExponent(r) >= getHighestExponent(d))) {
            Monomial t;
            t = r.monomialList.get(0).divide(d.monomialList.get(0));
            q.monomialList.add(t);
            r = r.addOrSubtractPolynomial(d.multiplyMonomialWithPolynomial(t), "subtract");
        }
        rez.add(q);
        rez.add(r);
        return rez;
    }


    public Polynomial derivatePolynomial() {
        Polynomial result = new Polynomial();
        for (Monomial monom : this.monomialList) {
            if (monom.getExponent() != 0) {
                double coeficient = monom.getCoeficient() * monom.getExponent();
                int exponent = monom.getExponent() - 1;
                result.monomialList.add(new Monomial(coeficient, exponent));
            }
        }
        return result;
    }


    public Polynomial integratePolynomial() {
        Polynomial result = new Polynomial();
        for (Monomial monom : this.monomialList) {
            double coeficient = monom.getCoeficient() / (monom.getExponent() + 1);
            int exponent = monom.getExponent() + 1;
            result.monomialList.add(new Monomial(coeficient, exponent));
        }
        return result;
    }


    public boolean isInList(Monomial a) {
        for (Monomial monom : this.monomialList) {
            if (a.getExponent() == monom.getExponent()) {
                return true;
            }
        }
        return false;
    }


    public int getHighestExponent(Polynomial p) {
        int max = -9999;
        for (Monomial monom: p.monomialList) {
            if (max < monom.getExponent()) {

                max = monom.getExponent();
            }
        }
        return max;
    }

    public Polynomial multiplyMonomialWithPolynomial(Monomial t) {
        Polynomial rez = new Polynomial();
        for (Monomial monom: this.monomialList) {
            rez.monomialList.add(monom.multiply(t));
        }
        return rez;
    }
    public Polynomial copy( Polynomial p){
        Polynomial result=new Polynomial();
        for(Monomial monom: p.monomialList){
            result.monomialList.add(monom);
        }
        return result;

 }

    public boolean equals(Polynomial b){
        int ok = 0;
        int i = 0;
        while (i < this.monomialList.size() && i < b.monomialList.size()) {
            if (!this.monomialList.get(i).equals(b.monomialList.get(i))) {
                ok = 1;
                break;
            }
            i++;
        }
        if (ok == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String s = "";
        for (Monomial monom: this.monomialList)
            s += monom.toString();
        return s;
    }
}