package br.com.jacto.schedulerservice.controller;

import br.com.jacto.schedulerservice.model.BaseResponseModel;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.service.SchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
    @Autowired
    private SchedulerService schedulerService;

    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel schedules() {
        try {
            return BaseResponseModel.successResult(schedulerService.getSchedules());
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @Operation(summary = "Create a new Schedule")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel create(@RequestBody VisitScheduleModel model) {
        try {
            VisitScheduleModel resultModel = schedulerService.create(model);
            return BaseResponseModel.successResult(resultModel);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel update(@RequestBody VisitScheduleModel model) {
        try {
            VisitScheduleModel resultModel = schedulerService.update(model);
            return BaseResponseModel.successResult(resultModel);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel delete(@RequestBody VisitScheduleModel model) {
        try {
            schedulerService.delete(model);
            return BaseResponseModel.successResult(model);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }
}
