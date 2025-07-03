package co.com.bancolombia.usecase.userinteractionstats;

import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserInteractionStatsUseCase {
    private final UserInteractionStatsGateway userInteractionStatsGateway;

    public Mono<Void> saveUserInteractionStats(UserInteractionStats userInteractionStats)
    {
        return userInteractionStatsGateway.saveUserInteractionStats(userInteractionStats)
                .doOnSuccess(response -> {System.out.println("success: Guardado exitoso");})
                .doOnError(response -> { System.out.println("error: Ocurrió un error"); });
    }
}
