package co.com.bancolombia.usecase.userinteractionstats;

import co.com.bancolombia.model.events.gateways.EventsGateway;
import co.com.bancolombia.model.exceptions.InvalidHashException;
import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import co.com.bancolombia.model.validators.IHashValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserInteractionStatsUseCase {
    private final UserInteractionStatsGateway userInteractionStatsGateway;
    private final EventsGateway eventsGateway;
    private final IHashValidator hashValidator;

    public Mono<Void> saveUserInteractionStats(UserInteractionStats userInteractionStats)
    {
        return Mono.just(userInteractionStats)
                .filter(hashValidator::isValid)
                .switchIfEmpty(Mono.error(new InvalidHashException("Hash inválido")))
                .flatMap(stat -> userInteractionStatsGateway.saveUserInteractionStats(stat)
                .then(eventsGateway.emit(stat)));
    }
}
