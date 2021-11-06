package co.com.sofka.estebang.events.handlers;

import co.com.sofka.estebang.model.generic.JobRepository;
import co.com.sofka.estebang.model.job.Job;
import co.com.sofka.estebang.model.job.Task;
import co.com.sofka.estebang.model.job.event.FinishDateAssigned;
import co.com.sofka.estebang.model.job.event.JobCreated;
import co.com.sofka.estebang.model.job.event.JobExecutionResult;
import co.com.sofka.estebang.model.job.event.TaskAssigned;
import lombok.AllArgsConstructor;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.impl.config.annotations.EnableCommandListeners;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@EnableCommandListeners
public class CommandsHandler {
    private final JobRepository repository;

    public Mono<Void> jobCreated(Command<JobCreated> command) {
        JobCreated event = command.getData();
        return repository.saveJob(Job.builder()
                .active(event.isActive())
                .cronExpression(event.getCronExpression())
                .startDate(event.getStartDate())
                .name(event.getName())
                .url(event.getUrl())
                .aggregateId(event.getAggregateId())
                .build());
    }

    public Mono<Void> taskAssigned(Command<TaskAssigned> command) {
        TaskAssigned event = command.getData();
        return repository.findJob(event.getAggregateId())
                .map(job -> job.addTask(Task.builder()
                        .taskId(event.getTaskId())
                        .status(event.getStatus())
                        .executionDate(event.getExecutionDate())
                        .build()))
                .flatMap(repository::saveJob);
    }

    public Mono<Void> finishDateAssigned(Command<FinishDateAssigned> command) {
        FinishDateAssigned event = command.getData();
        return repository.findJob(event.getAggregateId())
                .map(job -> job.toBuilder()
                        .finishDate(event.getFinishDate())
                        .build())
                .flatMap(repository::saveJob);
    }

    public Mono<Void> jobExecutionResult(Command<JobExecutionResult> command) {
        JobExecutionResult event = command.getData();
        return repository.findJob(event.getAggregateId())
                .map(job -> job.modifyStatusTask(event.getTaskId(), event.getStatus()))
                .flatMap(repository::saveJob);
    }
}
