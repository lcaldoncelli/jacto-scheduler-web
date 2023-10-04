package br.com.jacto.schedulerservice.model;

import br.com.jacto.schedulerservice.entity.AddressEntity;
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
    private long number;
    private String complement;
    private String district;
    private String city;
    private String state;

    public static AddressModel toModel(AddressEntity addressEntity) {
        return AddressModel.builder()
                .postalCode(addressEntity.getPostalCode())
                .street(addressEntity.getStreet())
                .number(addressEntity.getNumber())
                .complement(addressEntity.getComplement())
                .district(addressEntity.getDistrict())
                .city(addressEntity.getCity())
                .state(addressEntity.getState())
                .build();
    }
}
