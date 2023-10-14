package br.com.jacto.schedulerservice.controller;

import br.com.jacto.schedulerservice.model.AddressModel;
import br.com.jacto.schedulerservice.model.JsonDefaultResponseModel;
import br.com.jacto.schedulerservice.security.jwt.JwtUtils;
import br.com.jacto.schedulerservice.service.AddressService;
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

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "Updates Address schedule", description = "Updates an Address Schedule for an authenticated User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Scheduler Error"),
            @ApiResponse(responseCode = "401", description = "Authentication Failure"),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressModel.class))
                    } ) })
    @PostMapping(value = "/loadFromPostalCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> schedules(@RequestBody AddressModel model) {
        try {
            return ResponseEntity.ok(addressService.getAddressModel(model));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new JsonDefaultResponseModel(e.getMessage()));
        }
    }
}
