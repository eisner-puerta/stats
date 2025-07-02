package co.com.bancolombia.api;

import lombok.extern.log4j.Log4j2;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Map;

@Log4j2
@AllArgsConstructor
@Component
public class DefaultApiHandler {
//    private final UseCase someUseCase;

    public Mono<ServerResponse> statsPost(ServerRequest serverRequest) {
        return statsPostMock() // TODO: Call real use case here -> someUseCase.some()
            .flatMap(response -> ServerResponse.ok().bodyValue(response)); // TODO: Customize response here
    }

    private Mono<Void> statsPostMock() { // TODO: Remove this mock method
        return Mono.empty();
    }
}
