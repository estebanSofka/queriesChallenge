package co.com.sofka.estebang.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document(collection = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JobData {
    @Id
    private String aggregateId;
    private Set<TaskData> tasks;
    private String name;
    private String url;
    private String cronExpression;
    private boolean active;
    private Date startDate;
    private Date finishDate;
}
