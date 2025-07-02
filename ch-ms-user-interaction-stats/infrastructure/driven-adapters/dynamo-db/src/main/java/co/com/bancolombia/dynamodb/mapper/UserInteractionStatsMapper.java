package co.com.bancolombia.dynamodb.mapper;

import co.com.bancolombia.dynamodb.model.UserInteractionStatsDynamo;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserInteractionStatsMapper {

    UserInteractionStatsMapper INSTANCE = Mappers.getMapper(UserInteractionStatsMapper.class);

    @Mapping(target = "timestamp", ignore = true) // No está en el modelo de dominio
    UserInteractionStatsDynamo toEntity(UserInteractionStats model);
}
