package model;

public class Monomial {
    private int exponent;
    private double coeficient;

    public int getExponent() {
        return exponent;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public Monomial(double coeficient, int exponent) {
        this.coeficient = coeficient;
        this.exponent = exponent;
    }


    public Monomial addMonomial(Monomial monom1) {
        return new Monomial(this.coeficient + monom1.coeficient, this.exponent);
    }

    public Monomial subtract(Monomial monom1) {
        return new Monomial(this.coeficient - monom1.coeficient, monom1.exponent);
    }

    public Monomial multiply(Monomial monom) {
        return new Monomial(this.coeficient * monom.coeficient, this.exponent + monom.exponent);
    }

    public Monomial divide(Monomial monom) {
        return new Monomial(this.coeficient / monom.coeficient, this.exponent - monom.exponent);
    }

    public boolean equals(Monomial b) {
        if ((this.coeficient == b.getCoeficient()) && (this.exponent == b.getExponent())) {
            return true;
        } else {
            return false;
        }

    }

    public String toString() {
        if (this.coeficient == (int) this.coeficient) {
            if (this.coeficient >= 0) {
                if (this.exponent != 0) {
                    return "+" + (int) this.coeficient + "x^" + this.exponent;
                } else {
                    return "+" + (int) this.coeficient;
                }
            } else {
                if (this.exponent != 0) {
                    return  (int) this.coeficient + "x^" + this.exponent;
                } else {
                    return  ""+ (int) this.coeficient;
                }
            }
        } else {
            if (this.coeficient >= 0) {
                if (this.exponent != 0) {
                    return "+" + this.coeficient + "x^" + this.exponent;
                } else {
                    return "+" + this.coeficient;
                }
            } else {
                if (this.exponent != 0) {
                    return  + this.coeficient + "x^" + this.exponent;
                } else {
                    return "" + this.coeficient;
                }

            }
        }
    }
}
