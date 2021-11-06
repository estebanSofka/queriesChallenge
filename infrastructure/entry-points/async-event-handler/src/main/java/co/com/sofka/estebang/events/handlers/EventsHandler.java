package co.com.sofka.estebang.events.handlers;

import lombok.AllArgsConstructor;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.async.impl.config.annotations.EnableEventListeners;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@EnableEventListeners
public class EventsHandler {

    public Mono<Void> handleEventA(DomainEvent<co.com.sofka.estebang.model.generic.DomainEvent> event) {

        System.out.println("event received: " + event.getName() + " ->" + event.getData()); // TODO: Remove this line
//        return sampleUseCase.doSomething(event.getData());
        return Mono.empty();
    }
}
