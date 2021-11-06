package co.com.sofka.estebang.model.generic;

import co.com.sofka.estebang.model.job.Job;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JobRepository {

    Mono<Void> saveJob(Job job);
    Mono<Job> findJob(String jobId);
    //Flux<Job> findJobs(String aggregateId);

}
