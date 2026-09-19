package org.example.academic.system;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** Testes de igualdade dos objetos identificaveis (TUS-2384). */
class IdentifiableEqualityTest {

    @Test
    void classesWithSameCodeAreEqual() {
        AcademicClass first = new AcademicClass("CC101", "Orientacao a Objetos");
        AcademicClass second = new AcademicClass("CC101", "Titulo diferente");
        assertEquals(first, second);
    }

    @Test
    void classesWithSameCodeHaveSameHashCode() {
        AcademicClass first = new AcademicClass("CC101", "Orientacao a Objetos");
        AcademicClass second = new AcademicClass("CC101", "Titulo diferente");
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void classesWithDifferentCodesAreNotEqual() {
        assertNotEquals(new AcademicClass("CC101", "POO"),
                new AcademicClass("CC202", "POO"));
    }

    @Test
    void equalityWorksCorrectlyWithHashSet() {
        Set<AcademicClass> classes = new HashSet<>();
        classes.add(new AcademicClass("CC101", "Orientacao a Objetos"));
        classes.add(new AcademicClass("CC101", "Titulo diferente"));
        assertEquals(1, classes.size());
    }

    @Test
    void usersWithSameUsernameAreEqual() {
        User first = new User("gabriel", "senha1", Role.ADMIN);
        User second = new User("gabriel", "senha2", Role.PROFESSOR);
        assertEquals(first, second);
    }

    @Test
    void usersWithSameUsernameHaveSameHashCode() {
        User first = new User("gabriel", "senha1", Role.ADMIN);
        User second = new User("gabriel", "senha2", Role.PROFESSOR);
        assertEquals(first.hashCode(), second.hashCode());
    }
}
