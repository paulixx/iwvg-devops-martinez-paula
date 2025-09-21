package es.upm.miw.devops.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "Paula", "Martínez", new ArrayList<>());
    }

    @Test
    void testFullName() {
        assertThat(user.fullName()).isEqualTo("Paula Martínez");
    }

    @Test
    void testInitials() {
        assertThat(user.initials()).isEqualTo("P.");
    }

    @Test
    void testAddFraction() {
        Fraction fraction = new Fraction(); // ahora solo instanciamos
        user.addFraction(fraction);

        assertThat(user.getFractions())
                .hasSize(1)
                .containsExactly(fraction);
    }

    @Test
    void testSetAndGetName() {
        user.setName("Rodrigo");

        assertThat(user.getName()).isEqualTo("Rodrigo");
    }

    @Test
    void testSetAndGetFamilyName() {
        user.setFamilyName("Arce");

        assertThat(user.getFamilyName()).isEqualTo("Arce");
    }

    @Test
    void testFractionsNotNull() {
        User u2 = new User("2", "Ana", "López", Collections.emptyList());

        assertThat(u2.getFractions())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void testToStringContainsData() {
        assertThat(user.toString())
                .contains("Paula")
                .contains("Martínez")
                .contains("id='1'");
    }

    @Test
    void testEqualsAndHashCode() {
        User user2 = new User("1", "Paula", "Martínez", new ArrayList<>());

        assertThat(user).isEqualTo(user2);
    }
}
