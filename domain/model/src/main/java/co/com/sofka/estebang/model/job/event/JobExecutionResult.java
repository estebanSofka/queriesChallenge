package co.com.sofka.estebang.model.job.event;

import co.com.sofka.estebang.model.generic.DomainEvent;

import java.beans.ConstructorProperties;

public class JobExecutionResult extends DomainEvent {
    private String status;
    private String taskId;

    @ConstructorProperties({"status", "taskId"})
    public JobExecutionResult(String status, String taskId) {
        super("estebang.job.jobexecutionresult");
        this.status = status;
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public String getTaskId() {
        return taskId;
    }
}
