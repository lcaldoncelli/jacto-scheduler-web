package br.com.jacto.authservice.controller;

import br.com.jacto.authservice.model.BaseResponseModel;
import br.com.jacto.authservice.model.CreateUserModel;
import br.com.jacto.authservice.model.LoginModel;
import br.com.jacto.authservice.model.TokenResponseModel;
import br.com.jacto.authservice.service.AuthService;
import br.com.jacto.authservice.utils.LanguageUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Create new User")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel create(@RequestBody CreateUserModel model) {
        try {
            TokenResponseModel resultModel = authService.create(model);
            return BaseResponseModel.successResult(resultModel);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @Operation(summary = "Login")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel login(@RequestBody LoginModel model) {
        try {
            TokenResponseModel resultModel = authService.login(model);
            return BaseResponseModel.successResult(resultModel);
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }

    @Operation(summary = "Refresh token")
    @PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel refreshToken() {
        return BaseResponseModel.errorResult(LanguageUtils.NOT_YET_IMPLEMENTED, -1);
    }

    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "Hello Test";
    }
}
