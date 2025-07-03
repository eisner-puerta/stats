package co.com.bancolombia.model.userinteractionstats.gateways;

import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;
import reactor.core.publisher.Mono;

public interface UserInteractionStatsGateway {
    Mono<Void> saveUserInteractionStats(UserInteractionStats userInteractionStats);
}
