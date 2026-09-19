package org.example.academic.system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exemplo simples de teste que valida a infraestrutura de testes
 * (TUS-2383) e a inicializacao do Singleton AcademicSystem (US-0000).
 */
class ApplicationStartupTest {

    @Test
    void testingInfrastructureIsConfigured() {
        assertTrue(true);
    }

    @Test
    void academicSystemSingletonIsInitialized() {
        assertNotNull(AcademicSystem.getInstance());
    }

    @Test
    void academicSystemAlwaysReturnsTheSameInstance() {
        assertSame(AcademicSystem.getInstance(), AcademicSystem.getInstance());
    }
}
