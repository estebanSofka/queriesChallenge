package co.com.sofka.estebang.events;

import co.com.sofka.estebang.events.handlers.CommandsHandler;
import co.com.sofka.estebang.events.handlers.EventsHandler;
import co.com.sofka.estebang.events.handlers.QueriesHandler;
import co.com.sofka.estebang.model.job.event.FinishDateAssigned;
import co.com.sofka.estebang.model.job.event.JobCreated;
import co.com.sofka.estebang.model.job.event.JobExecutionResult;
import co.com.sofka.estebang.model.job.event.TaskAssigned;
import org.reactivecommons.async.api.HandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerRegistryConfiguration {

    // see more at: https://reactivecommons.org/reactive-commons-java/#_handlerregistry_2
    @Bean
    public HandlerRegistry handlerRegistry(CommandsHandler commands, EventsHandler events, QueriesHandler queries) {
        return HandlerRegistry.register()
                //.listenNotificationEvent("some.broadcast.event.name", events::handleEventA, Object.class/*change for proper model*/)
               // .listenEvent("send.materializatione", events::handleEventA, co.com.sofka.estebang.model.generic.DomainEvent.class/*change for proper model*/)
                .handleCommand("estebang.job.jobcreated", commands::jobCreated, JobCreated.class)
                .handleCommand("estebang.job.taskassigned", commands::taskAssigned, TaskAssigned.class)
                .handleCommand("estebang.job.jobexecutionresult", commands::jobExecutionResult, JobExecutionResult.class)
                .handleCommand("estebang.job.finishdateassigned", commands::finishDateAssigned, FinishDateAssigned.class);
                //.serveQuery("some.query.name", queries::handleQueryA, Object.class/*change for proper model*/);
    }
}
