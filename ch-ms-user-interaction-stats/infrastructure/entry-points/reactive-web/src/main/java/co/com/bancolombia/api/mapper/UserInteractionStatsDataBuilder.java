package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.UserInteractionStatsDTO;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserInteractionStatsDataBuilder {
    private final UserInteractionStatsMapperDTO userInteractionStatsMapperDTO;

    public Mono<UserInteractionStats> toModel(UserInteractionStatsDTO dto)
    {
        return Mono.just(userInteractionStatsMapperDTO.toModel(dto));
    }
}
