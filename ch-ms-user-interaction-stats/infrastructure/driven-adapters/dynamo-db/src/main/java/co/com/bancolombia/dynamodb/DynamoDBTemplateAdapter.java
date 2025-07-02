package co.com.bancolombia.dynamodb;

import co.com.bancolombia.dynamodb.mapper.UserInteractionStatsMapper;
import co.com.bancolombia.dynamodb.model.UserInteractionStatsDynamo;
import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository

public class DynamoDBTemplateAdapter implements UserInteractionStatsGateway {

    private final DynamoDbAsyncTable<UserInteractionStatsDynamo> table;
    private final UserInteractionStatsMapper mapper;

    public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, UserInteractionStatsMapper mapper) {
        this.table = connectionFactory.table("${aws.dynamodb.tables.user-interaction-stats}", TableSchema.fromBean(UserInteractionStatsDynamo.class));
        this.mapper = mapper;
    }

    @Override
    public Mono<Void> saveUserInteractionStats(UserInteractionStats model, String timestamp) {
        UserInteractionStatsDynamo entity = mapper.toEntity(model);
        return Mono.fromFuture(table.putItem(entity)).then();
    }
}
