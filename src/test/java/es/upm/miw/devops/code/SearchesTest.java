package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;
import es.upm.miw.devops.code.Searches;


import static org.assertj.core.api.Assertions.assertThat;

class SearchesTest {

    private final Searches searches = new Searches();

    @Test
    void testFindUserFamilyNameByUserNameDistinct() {
        assertThat(new Searches().findUserFamilyNameByUserNameDistinct("Paula").toList())
                .containsExactly("Torres");
    }

    @Test
    void testFindUserFractionNumeratorByFamilyName() {
        assertThat(new Searches().findFractionNumeratorByUserFamilyName("Torres").toList())
                .containsExactly(2, 4, 0, 1, 1);
    }

    @Test
    void testFindFamilyNameByFractionDenominator() {
        assertThat(new Searches().findUserFamilyNameByFractionDenominator(2).toList())
                .containsExactly("López", "Torres");
    }

    void testFindUserIdByAnyProperFraction() {
    }

    void testFindUserNameByAnyImproperFraction() {
    }

    void testFindUserFamilyNameByAllSignFractionDistinct() {
    }

    void testFindDecimalFractionByUserName() {
    }

    void testFindDecimalFractionBySignFraction() {
    }

    void testFindFractionAdditionByUserId() {
    }

    void testFindFractionSubtractionByUserName() {
    }

    void testFindFractionMultiplicationByUserFamilyName() {
    }

    @Test
    void testFindHighestFraction() {
        Fraction highest = searches.findHighestFraction();

        // Verificamos que no es nulo
        assertThat(highest).isNotNull();
        // Comprobamos que efectivamente es la mayor
        double maxValue = new UsersDatabase().findAll()
                .flatMap(u -> u.getFractions().stream())
                .mapToDouble(Fraction::decimal)
                .max()
                .orElseThrow();
        assertThat(highest.decimal()).isEqualTo(maxValue);
    }
}