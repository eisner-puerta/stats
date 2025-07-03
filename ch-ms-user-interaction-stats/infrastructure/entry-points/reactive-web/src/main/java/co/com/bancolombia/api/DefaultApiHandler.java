package co.com.bancolombia.api;

import co.com.bancolombia.api.config.ApiResponse;
import co.com.bancolombia.api.dto.UserInteractionStatsDTO;
import co.com.bancolombia.api.mapper.UserInteractionStatsDataBuilder;
import co.com.bancolombia.usecase.userinteractionstats.UserInteractionStatsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@Component
public class DefaultApiHandler {
    private final UserInteractionStatsUseCase userInteractionStatsUseCase;
    private final UserInteractionStatsDataBuilder userInteractionStatsDataBuilder;

    public Mono<ServerResponse> statsPost(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserInteractionStatsDTO.class)
            .flatMap(userInteractionStatsDataBuilder::toModel)
            .flatMap(userInteractionStats -> userInteractionStatsUseCase.saveUserInteractionStats(userInteractionStats))
            .flatMap(response -> ServerResponse.ok().bodyValue(response))
            .onErrorResume(IllegalArgumentException.class, ex ->
                    ServerResponse.badRequest().bodyValue(ApiResponse.builder()
                            .message("Hash no válido")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .timestamp(LocalDateTime.now())
                            .build())
            );
    }
}
