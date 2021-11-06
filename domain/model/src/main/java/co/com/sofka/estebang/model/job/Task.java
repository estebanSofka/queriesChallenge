package co.com.sofka.estebang.model.job;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
@Builder(toBuilder = true)
public class Task {
    private String taskId;
    private String status;
    private Date executionDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}
