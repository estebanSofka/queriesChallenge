package co.com.sofka.estebang.api;

import co.com.sofka.estebang.model.generic.JobRepository;
import co.com.sofka.estebang.model.job.Job;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class QueriesController {

    @Autowired
    private JobRepository repository;

    @Operation(summary = "CREATE JOB SERVICE WITH COMMAND", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "202",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Authentication Failure",
                    content = @Content(schema = @Schema(hidden = true)))})
    @GetMapping("/job/{id}")
    public Mono<Job> get(@PathVariable("id") String programId) {
        return repository.findJob(programId);
    }
}
