package co.com.sofka.estebang.model.job.event;

import co.com.sofka.estebang.model.generic.DomainEvent;

import java.util.Date;


public class JobCreated extends DomainEvent {

    private String name;
    private String url;
    private String cronExpression;
    private boolean active;
    private Date startDate;

    public JobCreated(){
        super("estebang.job.jobcreated");
    }

    public JobCreated(String name, String url, String cronExpression, boolean active, Date startDate) {
        super("estebang.job.jobcreated");
        this.name = name;
        this.url = url;
        this.cronExpression = cronExpression;
        this.active = active;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public boolean isActive() {
        return active;
    }

    public Date getStartDate() {
        return startDate;
    }
}
