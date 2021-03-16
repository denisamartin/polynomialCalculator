package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polynomial {
    private static final Pattern pattern = Pattern.compile("((-?\\d+(?=x))?(-?[xX])(\\^(-?\\d+))?)|((-?)[xX])|(-?\\d+)");
    private final ArrayList<Monomial> monomialList = new ArrayList<>();

    public static Polynomial extractMonomial(String input) {
        Matcher matcher = pattern.matcher(input);
        double coeficient;
        int exponent = 0;
        Polynomial polynomial = new Polynomial();
        while (matcher.find()) {
            if (matcher.group(3) != null && matcher.group(2) != null) {
                coeficient = Double.parseDouble(matcher.group(2));
                exponent = (matcher.group(5) != null ? Integer.parseInt(matcher.group(5)) : 1);

            } else {
                coeficient = Integer.parseInt(matcher.group());
            }
            Monomial monomial = new Monomial(coeficient, exponent);
            polynomial.monomialList.add(monomial);
            exponent = 0;
        }
        Collections.sort(polynomial.monomialList);
        return polynomial;
    }

    public Polynomial add(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        Polynomial polynomial1 = copy(this);
        Polynomial polynomial2 = copy(polynomial);
        int i = 0;
        int j = 0;
        while (i < this.monomialList.size() && j < polynomial.monomialList.size()) {
            Monomial monomial1 = this.monomialList.get(i);
            Monomial monomial2 = polynomial.monomialList.get(j);
            if (monomial1.getExponent() == monomial2.getExponent()) {
                polynomial1.monomialList.remove(monomial1);
                polynomial2.monomialList.remove(monomial2);
                result.monomialList.add(monomial1.add(monomial2));
                i++;
                j++;
            } else {
                if (monomial1.getExponent() > monomial2.getExponent()) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        result.monomialList.addAll(polynomial1.monomialList);
        result.monomialList.addAll(polynomial2.monomialList);
        Collections.sort(result.monomialList);
        return result;
    }


    public Polynomial subtract(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        Polynomial polynomial1 = copy(this);
        Polynomial polynomial2 = copy(polynomial);
        int i = 0;
        int j = 0;
        while (i < this.monomialList.size() && j < polynomial.monomialList.size()) {
            Monomial monomial1 = this.monomialList.get(i);
            Monomial monomial2 = polynomial.monomialList.get(j);
            if (monomial1.getExponent() == monomial2.getExponent()) {
                polynomial1.monomialList.remove(monomial1);
                polynomial2.monomialList.remove(monomial2);
                if (monomial1.getCoeficient() != monomial2.getCoeficient())
                    result.monomialList.add(monomial1.subtract(monomial2));
                i++;
                j++;
            } else {
                if (monomial1.getExponent() > monomial2.getExponent()) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        result.monomialList.addAll(polynomial1.monomialList);
        result.monomialList.addAll(polynomial2.monomialList);
        Collections.sort(result.monomialList);
        return result;
    }


    public Polynomial multiply(Polynomial polynomial) {
        Polynomial normalizedPolynomial = new Polynomial();
        Polynomial intermediar = new Polynomial();
        for (Monomial monomial1 : this.monomialList) {
            for (Monomial monomial2 : polynomial.monomialList) {
                intermediar.monomialList.add(monomial1.multiply(monomial2));
            }
        }
        normalizedPolynomial = normalize(intermediar, normalizedPolynomial);
        return normalizedPolynomial;
    }


    public ArrayList<Polynomial> divide(Polynomial polynomial) {
        ArrayList<Polynomial> result = new ArrayList<>();
        Polynomial quotient = new Polynomial();
        Polynomial remainder = this;
        while ((!remainder.monomialList.isEmpty()) && (getHighestExponent(remainder) >= getHighestExponent(polynomial))) {
            Monomial monomial = remainder.monomialList.get(0).divide(polynomial.monomialList.get(0));
            quotient.monomialList.add(monomial);
            remainder = remainder.subtract(polynomial.multiplyMonomialWithPolynomial(monomial));
        }
        result.add(quotient);
        result.add(remainder);
        return result;
    }


    public Polynomial differentiate() {
        Polynomial result = new Polynomial();
        for (Monomial monom : this.monomialList) {
            if (monom.getExponent() != 0) {
                result.monomialList.add(monom.differentiate());
            }
        }
        return result;
    }


    public Polynomial integrate() {
        Polynomial result = new Polynomial();
        for (Monomial monom : this.monomialList) {
            result.monomialList.add(monom.integrate());
        }
        return result;
    }


    public boolean hasMonomialWithSameExponent(Monomial monomial) {
        for (Monomial monom : this.monomialList) {
            if (monomial.getExponent() == monom.getExponent()) {
                return true;
            }
        }
        return false;
    }


    public int getHighestExponent(Polynomial polynomial) {
        int max = -9999;
        for (Monomial monom : polynomial.monomialList) {
            if (max < monom.getExponent()) {

                max = monom.getExponent();
            }
        }
        return max;
    }

    public Polynomial multiplyMonomialWithPolynomial(Monomial monomial) {
        Polynomial result = new Polynomial();
        for (Monomial monom : this.monomialList) {
            result.monomialList.add(monom.multiply(monomial));
        }
        return result;
    }


    public Polynomial copy(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        result.monomialList.addAll(polynomial.monomialList);
        return result;
    }

    public boolean equals(Polynomial polynomial) {
        int ok = 0;
        int i = 0;
        while (i < this.monomialList.size() && i < polynomial.monomialList.size()) {
            if (!this.monomialList.get(i).equals(polynomial.monomialList.get(i))) {
                ok = 1;
                break;
            }
            i++;
        }
        return ok == 0;
    }

    public Polynomial normalize(Polynomial intermediar, Polynomial normalizedPolynomial) {
        for (int i = 0; i < intermediar.monomialList.size(); i++) {
            if (!normalizedPolynomial.hasMonomialWithSameExponent(intermediar.monomialList.get(i))) {
                Monomial rezultatSumaInter = intermediar.monomialList.get(i);
                for (int j = i + 1; j < intermediar.monomialList.size() - 1; j++) {
                    if (intermediar.monomialList.get(i).getExponent() == intermediar.monomialList.get(j).getExponent()) {
                        rezultatSumaInter = rezultatSumaInter.add(intermediar.monomialList.get(j));
                    }
                }
                normalizedPolynomial.monomialList.add(rezultatSumaInter);
            }
        }
        return normalizedPolynomial;
    }

    public ArrayList<Monomial> getMonomialList() {
        return monomialList;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Monomial monom : this.monomialList)
            s.append(monom.toString());
        return s.toString();
    }
}