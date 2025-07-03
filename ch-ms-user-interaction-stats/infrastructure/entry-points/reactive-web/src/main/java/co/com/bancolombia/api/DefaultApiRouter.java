package co.com.bancolombia.api;

import lombok.extern.log4j.Log4j2;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Log4j2
@AllArgsConstructor
@Configuration
public class DefaultApiRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunctionDefaultApi(DefaultApiHandler handler) {
        return route(POST("/stats"), handler::statsPost)
;
    }
}
