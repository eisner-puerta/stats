package co.com.bancolombia.model.userinteractionstats;

import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInteractionStatsTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        UserInteractionStats stats = new UserInteractionStats(
                10, 2, 1, 3, 4, 0, 1, "hash-value"
        );

        assertEquals(10, stats.getTotalContactoClientes());
        assertEquals(2, stats.getMotivoReclamo());
        assertEquals(1, stats.getMotivoGarantia());
        assertEquals(3, stats.getMotivoDuda());
        assertEquals(4, stats.getMotivoCompra());
        assertEquals(0, stats.getMotivoFelicitaciones());
        assertEquals(1, stats.getMotivoCambio());
        assertEquals("hash-value", stats.getHash());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        UserInteractionStats stats = new UserInteractionStats();

        stats.setTotalContactoClientes(5);
        stats.setMotivoReclamo(1);
        stats.setMotivoGarantia(0);
        stats.setMotivoDuda(2);
        stats.setMotivoCompra(3);
        stats.setMotivoFelicitaciones(1);
        stats.setMotivoCambio(0);
        stats.setHash("test-hash");

        assertEquals(5, stats.getTotalContactoClientes());
        assertEquals(1, stats.getMotivoReclamo());
        assertEquals(0, stats.getMotivoGarantia());
        assertEquals(2, stats.getMotivoDuda());
        assertEquals(3, stats.getMotivoCompra());
        assertEquals(1, stats.getMotivoFelicitaciones());
        assertEquals(0, stats.getMotivoCambio());
        assertEquals("test-hash", stats.getHash());
    }

    @Test
    void testBuilder() {
        UserInteractionStats stats = UserInteractionStats.builder()
                .totalContactoClientes(15)
                .motivoReclamo(3)
                .motivoGarantia(2)
                .motivoDuda(1)
                .motivoCompra(4)
                .motivoFelicitaciones(0)
                .motivoCambio(1)
                .hash("builder-hash")
                .build();

        assertEquals(15, stats.getTotalContactoClientes());
        assertEquals(3, stats.getMotivoReclamo());
        assertEquals(2, stats.getMotivoGarantia());
        assertEquals(1, stats.getMotivoDuda());
        assertEquals(4, stats.getMotivoCompra());
        assertEquals(0, stats.getMotivoFelicitaciones());
        assertEquals(1, stats.getMotivoCambio());
        assertEquals("builder-hash", stats.getHash());
    }

    @Test
    void testEqualsAndHashCode() {
        UserInteractionStats stats1 = UserInteractionStats.builder()
                .totalContactoClientes(10)
                .motivoReclamo(2)
                .motivoGarantia(1)
                .motivoDuda(3)
                .motivoCompra(4)
                .motivoFelicitaciones(0)
                .motivoCambio(1)
                .hash("hash")
                .build();

        UserInteractionStats stats2 = UserInteractionStats.builder()
                .totalContactoClientes(10)
                .motivoReclamo(2)
                .motivoGarantia(1)
                .motivoDuda(3)
                .motivoCompra(4)
                .motivoFelicitaciones(0)
                .motivoCambio(1)
                .hash("hash")
                .build();

        assertEquals(stats1, stats2);
        assertEquals(stats1.hashCode(), stats2.hashCode());
    }

    @Test
    void testToString() {
        UserInteractionStats stats = UserInteractionStats.builder()
                .totalContactoClientes(10)
                .motivoReclamo(2)
                .motivoGarantia(1)
                .motivoDuda(3)
                .motivoCompra(4)
                .motivoFelicitaciones(0)
                .motivoCambio(1)
                .hash("hash")
                .build();

        String toString = stats.toString();

        assertTrue(toString.contains("totalContactoClientes=10"));
        assertTrue(toString.contains("hash=hash"));
    }
}
