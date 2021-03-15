package model;

public class Monomial implements Comparable<Monomial>{
    private final int exponent;
    private final double coeficient;

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


    public Monomial add(Monomial monom1) {
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

    public Monomial differentiate() {
        return new Monomial(this.getCoeficient() * this.getExponent(),this.getExponent() - 1 );
    }

    public Monomial integrate() {
        return new Monomial(this.getCoeficient() / (this.getExponent() + 1), this.getExponent() + 1);
    }

    public boolean equals(Monomial b) {
        return (this.coeficient == b.getCoeficient()) && (this.exponent == b.getExponent());
    }

    public int compareTo(Monomial x) {
        Integer exponent1= this.exponent;
        Integer exponent2= x.getExponent();
        return exponent2.compareTo(exponent1);
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
