package co.com.sofka.estebang.model.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    protected String aggregateId;
    protected Set<Task> tasks;
    protected String name;
    protected String url;
    protected String cronExpression;
    protected boolean active;
    protected Date startDate;
    protected Date finishDate;

    public Job addTask(Task task) {
        tasks.add(task);
        return this;
    }

    public Job modifyStatusTask(String taskId, String status) {
        return tasks.stream().filter(task -> task.getTaskId().equals(taskId))
                .findAny()
                .map(task -> {
                    tasks.remove(task);
                    return addTask(task.toBuilder().status(status).build());
                })
                .orElse(this);
    }
}
