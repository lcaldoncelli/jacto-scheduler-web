package br.com.jacto.authservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonDefaultResponseModel {
    public JsonDefaultResponseModel(String error) {
        this.error = error;
        this.message = error;
        this.status = 400;
    }
    Integer status;
    String error;
    String message;
}
