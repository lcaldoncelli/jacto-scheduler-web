package br.com.jacto.authservice.controller;

import br.com.jacto.authservice.model.UserModel;
import br.com.jacto.authservice.security.jwt.JwtUtils;
import br.com.jacto.authservice.service.AuthService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private AuthService authService;

    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "Get User", description = "Get User Data based on Authentication Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Auth Service Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserModel.class))
                    } ) })
    @PostMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        try {
            Claims claims = jwtUtils.getJwtClaimsFromRequestJwtToken(request);
            long userId = Long.parseLong(claims.getId());
            return ResponseEntity.ok(authService.getUser(userId));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
