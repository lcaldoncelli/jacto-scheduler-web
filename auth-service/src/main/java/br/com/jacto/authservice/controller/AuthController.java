package br.com.jacto.authservice.controller;

import br.com.jacto.authservice.model.*;
import br.com.jacto.authservice.service.AuthService;
import br.com.jacto.authservice.utils.LanguageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Create User", description = "Create user and returns token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Auth Service Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenResponseModel.class))
                    } ) })
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody CreateUserModel model) {
        try {
            TokenResponseModel resultModel = authService.create(model);
            return ResponseEntity.ok(resultModel);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new JsonDefaultResponseModel(e.getMessage()));
        }
    }

    @Operation(summary = "Login", description = "Authenticates user and returns token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Auth Service Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenResponseModel.class))
                    } ) })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody LoginModel model) {
        try {
            TokenResponseModel resultModel = authService.login(model);
            return ResponseEntity.ok(resultModel);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new JsonDefaultResponseModel(e.getMessage()));
        }
    }
}
