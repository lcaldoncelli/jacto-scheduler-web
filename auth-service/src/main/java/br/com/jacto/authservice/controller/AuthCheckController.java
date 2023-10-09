package br.com.jacto.authservice.controller;

import br.com.jacto.authservice.model.BaseResponseModel;
import br.com.jacto.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
public class AuthCheckController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Check authentication Token")
    @GetMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel create() {
        try {
            return BaseResponseModel.successResult("Success");
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }
}
