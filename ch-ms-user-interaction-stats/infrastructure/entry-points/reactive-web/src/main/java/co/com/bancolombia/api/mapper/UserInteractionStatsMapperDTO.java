package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.UserInteractionStatsDTO;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserInteractionStatsMapperDTO {

    UserInteractionStatsMapperDTO INSTANCE = Mappers.getMapper(UserInteractionStatsMapperDTO.class);

    UserInteractionStats toModel(UserInteractionStatsDTO dto);
}
