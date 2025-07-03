package co.com.bancolombia.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.api.domain.DomainEventBus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReactiveEventsGatewayTest {

    @Mock
    private DomainEventBus domainEventBus;

    private ObjectMapper objectMapper;

    private ReactiveEventsGateway gateway;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        gateway = new ReactiveEventsGateway(domainEventBus, objectMapper);
    }

    @Test
    void emit_shouldSendEventSuccessfully() {
        // Arrange
        Object event = Map.of("key", "value");
        when(domainEventBus.emit(any(CloudEvent.class))).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(gateway.emit(event))
                .verifyComplete();

        // Verify CloudEvent sent
        ArgumentCaptor<CloudEvent> captor = ArgumentCaptor.forClass(CloudEvent.class);
        verify(domainEventBus).emit(captor.capture());

        CloudEvent cloudEvent = captor.getValue();
        assertEquals(ReactiveEventsGateway.EVENT_NAME, cloudEvent.getType());
        assertEquals("urn:microservice:ch-ms-user-interaction-stats", cloudEvent.getSource().toString());
    }

    @Test
    void emit_shouldSerializeEventToJson() {
        // Arrange
        Map<String, Object> event = Map.of("testField", "testValue");
        when(domainEventBus.emit(any(CloudEvent.class))).thenReturn(Mono.empty());

        // Act
        gateway.emit(event).block();

        // Assert JSON serialization worked
        ArgumentCaptor<CloudEvent> captor = ArgumentCaptor.forClass(CloudEvent.class);
        verify(domainEventBus).emit(captor.capture());

        CloudEvent cloudEvent = captor.getValue();
        byte[] data = cloudEvent.getData().toBytes();
        String json = new String(data);
        assertEquals("{\"testField\":\"testValue\"}", json);
    }
}
