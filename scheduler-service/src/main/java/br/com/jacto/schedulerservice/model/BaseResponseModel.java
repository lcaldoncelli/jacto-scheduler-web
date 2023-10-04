package br.com.jacto.schedulerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseModel {

    private String result;
    private String description;
    private int errorCode;
    private Object data;

    public static BaseResponseModel successResult(Object data) {
        return BaseResponseModel.builder()
                .result("Success")
                .errorCode(0)
                .data(data)
                .build();
    }

    public static BaseResponseModel errorResult(String description, int errorCode) {
        return BaseResponseModel.builder()
                .result("Error")
                .description(description)
                .errorCode(errorCode)
                .build();
    }
}
