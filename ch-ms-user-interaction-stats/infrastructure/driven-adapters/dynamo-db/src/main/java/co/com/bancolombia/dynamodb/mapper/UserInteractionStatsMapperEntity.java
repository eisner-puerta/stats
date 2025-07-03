package co.com.bancolombia.dynamodb.mapper;

import co.com.bancolombia.dynamodb.model.UserInteractionStatsDynamo;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserInteractionStatsMapperEntity {

    UserInteractionStatsMapperEntity INSTANCE = Mappers.getMapper(UserInteractionStatsMapperEntity.class);

    UserInteractionStatsDynamo toEntity(UserInteractionStats model);
}
