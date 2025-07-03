package co.com.bancolombia.dynamodb;

import co.com.bancolombia.dynamodb.mapper.UserInteractionStatsMapperEntity;
import co.com.bancolombia.dynamodb.model.UserInteractionStatsDynamo;
import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.LocalDateTime;

@Repository

public class DynamoDBTemplateAdapter implements UserInteractionStatsGateway {

    private final DynamoDbAsyncTable<UserInteractionStatsDynamo> table;
    private final UserInteractionStatsMapperEntity userInteractionStatsMapperEntity;

    public DynamoDBTemplateAdapter(DynamoDbEnhancedAsyncClient connectionFactory, UserInteractionStatsMapperEntity userInteractionStatsMapperEntity,@Value("${aws.dynamodb.tables.user-interaction-stats}") String tableName) {
        this.table = connectionFactory.table(tableName, TableSchema.fromBean(UserInteractionStatsDynamo.class));
        this.userInteractionStatsMapperEntity = userInteractionStatsMapperEntity;
    }

    public Mono<Void> saveUserInteractionStats(UserInteractionStats model) {
        UserInteractionStatsDynamo entity = userInteractionStatsMapperEntity.toEntity(model);
        entity.setTimestamp(LocalDateTime.now().toString());
        return Mono.fromFuture(table.putItem(entity)).then();
    }
}
