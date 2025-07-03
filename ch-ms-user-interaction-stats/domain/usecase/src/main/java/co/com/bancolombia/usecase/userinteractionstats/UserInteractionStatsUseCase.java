package co.com.bancolombia.usecase.userinteractionstats;

import co.com.bancolombia.model.events.gateways.EventsGateway;
import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import co.com.bancolombia.model.validators.HashValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserInteractionStatsUseCase {
    private final UserInteractionStatsGateway userInteractionStatsGateway;
    private final EventsGateway eventsGateway;

    public Mono<Void> saveUserInteractionStats(UserInteractionStats userInteractionStats)
    {
        return Mono.just(userInteractionStats)
                .filter(HashValidator.isValid::apply)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Hash inválido")))
                .flatMap(stat -> userInteractionStatsGateway.saveUserInteractionStats(stat)
                .then(eventsGateway.emit(stat)));
    }
}
