package co.com.bancolombia.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    STATS_VALIDATED("event.stats.validated");

    private final String name;
}
