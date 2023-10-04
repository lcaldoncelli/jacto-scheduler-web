package br.com.jacto.schedulerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
    private long postalCode;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
}
