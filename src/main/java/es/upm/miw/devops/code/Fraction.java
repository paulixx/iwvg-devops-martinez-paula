package es.upm.miw.devops.code;

/**
 * Conceptos: Las fracciones propias son aquellas cuyo numerador es menor que el denominador
 * <p>
 * Las fracciones impropias son aquellas cuyo numerador es mayor que el denominador
 * <p>
 * Dos fracciones son equivalentes cuando el producto de extremos (numerador de la primera por denominador de la segunda) es igual al
 * producto de medios (denominador de la primera por el numerador de la segunda)
 * <p>
 * Las fracciones irreducibles son aquellas que no se pueden simplificar, esto sucede cuando el numerador y el denominador son primos entre
 * sí
 * <p>
 * Reducir varias fracciones a común denominador consiste en convertirlas en otras equivalentes que tengan el mismo denominador
 * <p>
 * Comparar fracciones
 * <p>
 * Suma fracciones: En primer lugar se reducen los denominadores a común denominador, y se suman o se restan los numeradores de las
 * fracciones equivalentes obtenidas
 * <p>
 * Multiplicación: La multiplicación de dos fracciones es otra fracción que tiene: Por numerador el producto de los numeradores. Por
 * denominador el producto de los denominadores.
 * <p>
 * La división de dos fracciones es otra fracción que tiene: Por numerador el producto de los extremos. Por denominador el producto de los
 * medios. Invertir fraccion
 */

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double decimal() {
        return (double) numerator / denominator;
    }

    //NUEVAS FUNCIONALIDADES

    /** Fracción propia: numerador < denominador (en valor absoluto) */
    public boolean isProper() {
        return Math.abs(numerator) < Math.abs(denominator);
    }

    /** Fracción impropia: numerador > denominador (en valor absoluto) */
    public boolean isImproper() {
        return Math.abs(numerator) > Math.abs(denominator);
    }

    /** Dos fracciones son equivalentes si a*d = b*c */
    public boolean isEquivalent(Fraction other) {
        return this.numerator * other.denominator == this.denominator * other.numerator;
    }

    /** Suma de fracciones */
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator).simplify();
    }

    /** Multiplicación de fracciones */
    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator).simplify();
    }

    /** División de fracciones */
    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Cannot divide by fraction with numerator 0");
        }
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator).simplify();
    }

    //Metodo de apoyo para simplificar fracciones
    private int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    private Fraction simplify() {
        int gcd = gcd(this.numerator, this.denominator);
        return new Fraction(this.numerator / gcd, this.denominator / gcd);
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}