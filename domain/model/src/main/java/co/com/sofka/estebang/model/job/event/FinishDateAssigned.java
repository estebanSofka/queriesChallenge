package co.com.sofka.estebang.model.job.event;

import co.com.sofka.estebang.model.generic.DomainEvent;

import java.beans.ConstructorProperties;
import java.util.Date;


public class FinishDateAssigned extends DomainEvent {
    private Date finishDate;

    @ConstructorProperties("finishDate")
    public FinishDateAssigned(Date finishDate) {
        super("estebang.job.finishdateassigned");
        this.finishDate = finishDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }
}
