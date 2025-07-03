package co.com.bancolombia.dynamodb;

import co.com.bancolombia.dynamodb.mapper.UserInteractionStatsMapperEntity;
import co.com.bancolombia.dynamodb.model.UserInteractionStatsDynamo;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DynamoDBTemplateAdapterTest {

    @Mock
    private DynamoDbEnhancedAsyncClient connectionFactory;

    @Mock
    private UserInteractionStatsMapperEntity mapper;

    @Mock
    private DynamoDbAsyncTable<UserInteractionStatsDynamo> table;

    private DynamoDBTemplateAdapter adapter;

    @BeforeEach
    void setUp() {
        when(connectionFactory.table(anyString(), any(TableSchema.class))).thenReturn(table);
        adapter = new DynamoDBTemplateAdapter(connectionFactory, mapper, "test-table");
    }

    @Test
    void saveUserInteractionStats_shouldSaveSuccessfully() {
        // Arrange
        UserInteractionStats model = new UserInteractionStats();
        UserInteractionStatsDynamo entity = new UserInteractionStatsDynamo();

        when(mapper.toEntity(model)).thenReturn(entity);
        when(table.putItem(any(UserInteractionStatsDynamo.class)))
                .thenReturn(CompletableFuture.completedFuture(null));

        // Act & Assert
        StepVerifier.create(adapter.saveUserInteractionStats(model))
                .verifyComplete();

        // Validate mapping & save
        verify(mapper).toEntity(model);
        verify(table).putItem(entity);

        // Validate that timestamp was set
        assertNotNull(entity.getTimestamp());
    }
}
