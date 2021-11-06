package co.com.sofka.estebang.mongo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JobDataRepository extends ReactiveCrudRepository<JobData, String> {
    Mono<JobData>findByAggregateId(String aggregateId);
}
