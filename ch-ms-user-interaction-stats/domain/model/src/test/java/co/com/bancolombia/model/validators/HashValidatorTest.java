package co.com.bancolombia.model.validators;

import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashValidatorTest {

    private HashValidator hashValidator;
    private UserInteractionStats stats;

    @BeforeEach
    void setUp() {
        hashValidator = new HashValidator();

        stats = new UserInteractionStats();
        stats.setTotalContactoClientes(10);
        stats.setMotivoReclamo(2);
        stats.setMotivoGarantia(1);
        stats.setMotivoDuda(3);
        stats.setMotivoCompra(4);
        stats.setMotivoFelicitaciones(0);
        stats.setMotivoCambio(1);
    }

    @Test
    void generateHash_shouldReturnCorrectHash() {
        String hash = hashValidator.generateHash(stats);
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }

    @Test
    void isValid_shouldReturnTrue_whenHashIsCorrect() {
        String hash = hashValidator.generateHash(stats);
        stats.setHash(hash);

        boolean valid = hashValidator.isValid(stats);
        assertTrue(valid);
    }

    @Test
    void isValid_shouldReturnFalse_whenHashIsIncorrect() {
        stats.setHash("invalid-hash");

        boolean valid = hashValidator.isValid(stats);
        assertFalse(valid);
    }
}