package br.com.jacto.schedulerservice.controller;

import br.com.jacto.schedulerservice.model.BaseResponseModel;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.security.jwt.JwtUtils;
import br.com.jacto.schedulerservice.service.SchedulerService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel schedules(HttpServletRequest request) {
        try {
            return BaseResponseModel.successResult(schedulerService.getSchedules(getUserId(request)));
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @Operation(summary = "Create a new Schedule")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel create(@RequestBody VisitScheduleModel model, HttpServletRequest request) {
        try {
            VisitScheduleModel resultModel = schedulerService.create(model, getUserId(request));
            return BaseResponseModel.successResult(resultModel);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel update(@RequestBody VisitScheduleModel model, HttpServletRequest request) {
        try {
            VisitScheduleModel resultModel = schedulerService.update(model, getUserId(request));
            return BaseResponseModel.successResult(resultModel);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel delete(@RequestBody VisitScheduleModel model, HttpServletRequest request) {
        try {
            schedulerService.delete(model, getUserId(request));
            return BaseResponseModel.successResult(model);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    private long getUserId(HttpServletRequest request) {
        Claims claims = jwtUtils.getJwtClaimsFromRequestJwtToken(request);
        return Long.parseLong(claims.getId());
    }
}
