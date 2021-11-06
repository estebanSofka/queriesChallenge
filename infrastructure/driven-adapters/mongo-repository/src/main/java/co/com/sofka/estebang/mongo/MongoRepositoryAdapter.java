package co.com.sofka.estebang.mongo;

import co.com.sofka.estebang.model.generic.JobRepository;
import co.com.sofka.estebang.model.job.Job;
import co.com.sofka.estebang.model.job.Task;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Repository
public class MongoRepositoryAdapter implements JobRepository {

    @Autowired
    private JobDataRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Mono<Void> saveJob(Job job) {
        return repository.save(toData(job))
                .then();
    }

    @Override
    public Mono<Job> findJob(String jobId) {
        return repository.findByAggregateId(jobId)
                .map(this::toEntity);
    }

    private Job toEntity(JobData jobData) {
        return Job.builder()
                .finishDate(jobData.getFinishDate())
                .tasks(jobData.getTasks() != null ? tasksToEntity(jobData.getTasks()) : new HashSet<>())
                .url(jobData.getUrl())
                .name(jobData.getName())
                .startDate(jobData.getStartDate())
                .cronExpression(jobData.getCronExpression())
                .active(jobData.isActive())
                .aggregateId(jobData.getAggregateId())
                .build();
    }

    private JobData toData(Job job) {
        return JobData.builder()
                .finishDate(job.getFinishDate())
                .tasks(job.getTasks() != null ? tasksToData(job.getTasks()) : new HashSet<>())
                .url(job.getUrl())
                .name(job.getName())
                .startDate(job.getStartDate())
                .cronExpression(job.getCronExpression())
                .active(job.isActive())
                .aggregateId(job.getAggregateId())
                .build();
    }

    private Set<Task> tasksToEntity(Set<TaskData> tasksData) {
        Set<Task> tasks = new HashSet<>();
        tasksData.forEach(v -> {
            tasks.add(mapper.mapBuilder(v, Task.TaskBuilder.class).build());
        });
        return tasks;
    }

    private Set<TaskData> tasksToData(Set<Task> tasks) {
        Set<TaskData> tasksData = new HashSet<>();
        tasks.forEach(v -> {
            tasksData.add(mapper.mapBuilder(v, TaskData.TaskDataBuilder.class).build());
        });
        return tasksData;
    }
}
