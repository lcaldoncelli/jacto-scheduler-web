package br.com.jacto.schedulerservice.controller;

import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {
    @Autowired
    private SchedulerService schedulerService;

    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<VisitScheduleModel> schedules() {
        return schedulerService.getSchedules();
    }
}
