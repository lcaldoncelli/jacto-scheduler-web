package br.com.jacto.schedulerservice.controller;

import br.com.jacto.schedulerservice.model.DeleteScheduleModel;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.security.jwt.JwtUtils;
import br.com.jacto.schedulerservice.service.SchedulerService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "Get list of schedules", description = "Gets the list of schedules for an authenticated User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Scheduler Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = VisitScheduleModel.class)))
            } ) })
    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> schedules(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(schedulerService.getSchedules(getUserId(request)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Create new schedule", description = "Creates a new Schedule for an authenticated User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Scheduler Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VisitScheduleModel.class))
                    } ) })
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody VisitScheduleModel model, HttpServletRequest request) {
        try {
            VisitScheduleModel resultModel = schedulerService.create(model, getUserId(request));
            return ResponseEntity.ok(resultModel);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Updates existing schedule", description = "Updates a Schedule for an authenticated User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Scheduler Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VisitScheduleModel.class))
                    } ) })
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody VisitScheduleModel model, HttpServletRequest request) {
        try {
            VisitScheduleModel resultModel = schedulerService.update(model, getUserId(request));
            return ResponseEntity.ok(resultModel);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Removes existing schedule", description = "Removes a Schedule for an authenticated User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Scheduler Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DeleteScheduleModel.class))
                    } ) })
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@RequestBody DeleteScheduleModel model, HttpServletRequest request) {
        try {
            schedulerService.delete(model.getScheduleId(), getUserId(request));
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    private long getUserId(HttpServletRequest request) {
        Claims claims = jwtUtils.getJwtClaimsFromRequestJwtToken(request);
        return Long.parseLong(claims.getId());
    }
}
