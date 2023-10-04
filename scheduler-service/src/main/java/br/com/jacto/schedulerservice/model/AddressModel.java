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
    private long id;
    private long postalCode;
    private String street;
    private long number;
    private String complement;
    private String district;
    private String city;
    private String state;

    public static AddressModel toModel(AddressEntity entity) {
        return AddressModel.builder()
                .id(entity.getId())
                .postalCode(entity.getPostalCode())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .complement(entity.getComplement())
                .district(entity.getDistrict())
                .city(entity.getCity())
                .state(entity.getState())
                .build();
    }

    public static AddressEntity toEntity(AddressModel model) {
        return AddressEntity.builder()
                .id(model.getId())
                .postalCode(model.getPostalCode())
                .street(model.getStreet())
                .number(model.getNumber())
                .complement(model.getComplement())
                .district(model.getDistrict())
                .city(model.getCity())
                .state(model.getState())
                .build();
    }
}
