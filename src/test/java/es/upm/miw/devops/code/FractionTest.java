package es.upm.miw.devops.code;

import es.upm.miw.devops.code.Fraction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.within;


import static org.assertj.core.api.Assertions.assertThat;

class FractionTest {

    @Test
    void testDefaultConstructor() {
        Fraction fraction = new Fraction();
        assertThat(fraction.getNumerator()).isEqualTo(1);
        assertThat(fraction.getDenominator()).isEqualTo(1);
    }

    @Test
    void testConstructorWithParameters() {
        Fraction fraction = new Fraction(3, 5);
        assertThat(fraction.getNumerator()).isEqualTo(3);
        assertThat(fraction.getDenominator()).isEqualTo(5);
    }

    @Test
    void testSettersAndGetters() {
        Fraction fraction = new Fraction();
        fraction.setNumerator(7);
        fraction.setDenominator(8);

        assertThat(fraction.getNumerator()).isEqualTo(7);
        assertThat(fraction.getDenominator()).isEqualTo(8);
    }

    @Test
    void testDecimalValue() {
        Fraction fraction = new Fraction(1, 2);
        assertThat(fraction.decimal()).isEqualTo(0.5);

        Fraction fraction2 = new Fraction(3, 4);
        assertThat(fraction2.decimal()).isCloseTo(0.75, within(1e-6));
    }

    @Test
    void testToString() {
        Fraction fraction = new Fraction(2, 3);
        assertThat(fraction.toString())
                .contains("numerator=2")
                .contains("denominator=3");
    }

    //NUEVAS FUNCIONALIDADES
    @Test
    void testIsProper() {
        assertThat(new Fraction(3, 4).isProper()).isTrue();
        assertThat(new Fraction(5, 3).isProper()).isFalse();
    }

    @Test
    void testIsImproper() {
        assertThat(new Fraction(5, 3).isImproper()).isTrue();
        assertThat(new Fraction(3, 5).isImproper()).isFalse();
    }

    @Test
    void testIsEquivalent() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = new Fraction(3, 5);

        assertThat(f1.isEquivalent(f2)).isTrue();
        assertThat(f1.isEquivalent(f3)).isFalse();
    }

    @Test
    void testAdd() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);

        Fraction result = f1.add(f2);
        assertThat(result.getNumerator()).isEqualTo(5);
        assertThat(result.getDenominator()).isEqualTo(6);
    }

    @Test
    void testMultiply() {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(3, 5);

        Fraction result = f1.multiply(f2);
        assertThat(result.getNumerator()).isEqualTo(2);
        assertThat(result.getDenominator()).isEqualTo(5);
    }

    @Test
    void testDivide() {
        Fraction f1 = new Fraction(3, 4);
        Fraction f2 = new Fraction(2, 5);

        Fraction result = f1.divide(f2);
        assertThat(result.getNumerator()).isEqualTo(15);
        assertThat(result.getDenominator()).isEqualTo(8);
    }

}
