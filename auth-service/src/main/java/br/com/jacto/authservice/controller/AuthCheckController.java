package br.com.jacto.authservice.controller;

import br.com.jacto.authservice.model.BaseResponseModel;
import br.com.jacto.authservice.security.jwt.JwtUtils;
import br.com.jacto.authservice.service.AuthService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
@Slf4j
public class AuthCheckController {
    @Autowired
    private AuthService authService;

    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "Check authentication Token")
    @GetMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseModel checkToken(HttpServletRequest request) {
        try {
            Claims claims = jwtUtils.getJwtClaimsFromRequestJwtToken(request);
            log.info("Request user {} , id {}", claims.getSubject(), claims.getId());

            return BaseResponseModel.successResult("Success");
        } catch (Exception e) {
            return BaseResponseModel.errorResult(e.getMessage(), -1);
        }
    }
}
