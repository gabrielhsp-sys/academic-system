package org.example.academic.system;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Verifica o comportamento da infraestrutura de logging (TUS-2395),
 * sem depender de conteudo ou destino especifico das mensagens.
 */
class LoggingInfrastructureTest {

    @Test
    void loggerInstanceCanBeCreated() {
        assertNotNull(LoggerFactory.getLogger(LoggingInfrastructureTest.class));
    }

    @Test
    void logMessagesCanBeWrittenWithoutExceptions() {
        Logger logger = LoggerFactory.getLogger(LoggingInfrastructureTest.class);
        assertDoesNotThrow(() -> {
            logger.info("mensagem informativa de teste");
            logger.warn("mensagem de aviso de teste");
            logger.error("mensagem de erro de teste");
        });
    }

    @Test
    void auditLoggerCanBeUsed() {
        Logger audit = LoggerFactory.getLogger("AUDIT");
        assertNotNull(audit);
        assertDoesNotThrow(() -> audit.info("evento de auditoria de teste"));
    }
}
