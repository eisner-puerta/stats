package co.com.bancolombia.usecase.userinteractionstats;

import co.com.bancolombia.model.events.gateways.EventsGateway;
import co.com.bancolombia.model.exceptions.InvalidHashException;
import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import co.com.bancolombia.model.validators.IHashValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserInteractionStatsUseCaseTest {

    @Mock
    private UserInteractionStatsGateway userInteractionStatsGateway;

    @Mock
    private EventsGateway eventsGateway;

    @Mock
    private IHashValidator hashValidator;

    @InjectMocks
    private UserInteractionStatsUseCase useCase;

    private UserInteractionStats stats;

    @BeforeEach
    void setUp() {
        stats = new UserInteractionStats();
        stats.setTotalContactoClientes(10);
        stats.setMotivoReclamo(2);
        stats.setMotivoGarantia(1);
        stats.setMotivoDuda(3);
        stats.setMotivoCompra(4);
        stats.setMotivoFelicitaciones(0);
        stats.setMotivoCambio(1);
        stats.setHash("dummyhash");
    }

    @Test
    void saveUserInteractionStats_shouldSaveAndEmit_whenValidHash() {
        // Arrange
        Mockito.when(hashValidator.isValid(any())).thenReturn(true);
        Mockito.when(userInteractionStatsGateway.saveUserInteractionStats(any()))
                .thenReturn(Mono.empty());
        Mockito.when(eventsGateway.emit(any()))
                .thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.saveUserInteractionStats(stats))
                .verifyComplete();

        Mockito.verify(userInteractionStatsGateway).saveUserInteractionStats(stats);
        Mockito.verify(eventsGateway).emit(stats);
    }

    @Test
    void saveUserInteractionStats_shouldThrowInvalidHashException_whenInvalidHash() {
        // Arrange
        Mockito.when(hashValidator.isValid(any())).thenReturn(false);

        // Act & Assert
        StepVerifier.create(useCase.saveUserInteractionStats(stats))
                .expectError(InvalidHashException.class)
                .verify();

        Mockito.verifyNoInteractions(userInteractionStatsGateway);
        Mockito.verifyNoInteractions(eventsGateway);
    }
}
