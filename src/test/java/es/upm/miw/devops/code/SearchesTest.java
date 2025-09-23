package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;
import es.upm.miw.devops.code.Searches;
import java.util.List;



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

        assertThat(highest).isNotNull();

        double maxValue = new UsersDatabase().findAll()
                .flatMap(u -> u.getFractions().stream())
                .filter(f -> f != null && f.getDenominator() != 0) // ignoramos inválidas
                .mapToDouble(Fraction::decimal)
                .max()
                .orElseThrow();

        assertThat(highest.decimal()).isEqualTo(maxValue);
    }

    @Test
    void testFindUserNameBySomeImproperFraction() {
        List<String> result = searches.findUserNameBySomeImproperFraction().toList();

        assertThat(result).isNotEmpty(); // debe devolver al menos un usuario

        // Todos los usuarios devueltos tienen alguna fracción impropia válida
        result.forEach(name -> {
            boolean hasImproper = new UsersDatabase().findAll()
                    .filter(u -> u.getName().equals(name))
                    .flatMap(u -> u.getFractions().stream())
                    .filter(f -> f != null && f.getDenominator() != 0) // filtramos inválidas
                    .anyMatch(Fraction::isImproper);

            assertThat(hasImproper)
                    .as("Usuario %s debería tener al menos una fracción impropia", name)
                    .isTrue();
        });
    }

    @Test
    void testFindFirstDecimalFractionByUserName() {
        String targetName = "user1"; // cámbialo por un nombre real de tu UsersDatabase
        Double firstDecimal = searches.findFirstDecimalFractionByUserName(targetName);

        // Calculamos el esperado directamente de la UsersDatabase
        Double expected = new UsersDatabase().findAll()
                .filter(u -> u.getName().equals(targetName))
                .flatMap(u -> u.getFractions().stream())
                .filter(f -> f != null && f.getDenominator() != 0)
                .map(Fraction::decimal)
                .findFirst()
                .orElse(null);

        // Comparamos valores
        assertThat(firstDecimal).isEqualTo(expected);
    }

    @Test
    void testFindDecimalFractionByNegativeSignFraction() {
        List<Double> result = searches.findDecimalFractionByNegativeSignFraction().toList();

        // Verificamos que todas las fracciones devueltas son negativas
        assertThat(result).allSatisfy(value -> assertThat(value).isLessThan(0));

        // Calculamos el esperado directamente desde UsersDatabase
        List<Double> expected = new UsersDatabase().findAll()
                .flatMap(u -> u.getFractions().stream())
                .filter(f -> f != null && f.getDenominator() != 0)
                .filter(f -> f.getNumerator() * f.getDenominator() < 0)
                .map(Fraction::decimal)
                .toList();

        // Comparamos los resultados
        assertThat(result).containsExactlyElementsOf(expected);
    }

}