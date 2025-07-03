package co.com.bancolombia.usecase.userinteractionstats;

import co.com.bancolombia.model.events.gateways.EventsGateway;
import co.com.bancolombia.model.userinteractionstats.gateways.UserInteractionStatsGateway;
import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserInteractionStatsUseCase {
    private final UserInteractionStatsGateway userInteractionStatsGateway;
    private final EventsGateway eventsGateway;

    public Mono<Void> saveUserInteractionStats(UserInteractionStats userInteractionStats)
    {
        return userInteractionStatsGateway.saveUserInteractionStats(userInteractionStats)
                .then(eventsGateway.emit(userInteractionStats));
    }
}
