package br.com.jacto.schedulerservice.controller;

import br.com.jacto.schedulerservice.model.AddressModel;
import br.com.jacto.schedulerservice.model.BaseResponseModel;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.security.jwt.JwtUtils;
import br.com.jacto.schedulerservice.service.AddressService;
import br.com.jacto.schedulerservice.service.SchedulerService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping(value = "/loadFromPostalCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel schedules(AddressModel model) {
        try {
            return BaseResponseModel.successResult(addressService.getAddressModel(model));
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }
}
