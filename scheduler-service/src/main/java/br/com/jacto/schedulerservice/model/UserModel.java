package br.com.jacto.schedulerservice.model;

import br.com.jacto.schedulerservice.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private long id;
    private String email;
    private String name;
    private String phoneNumber;
    private UserRole role;

    public static UserModel toModel(UserEntity entity) {
        return UserModel.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .build();
    }

    public static UserEntity toEntity(UserModel model) {
        return UserEntity.builder()
                .id(model.getId())
                .email(model.getEmail())
                .name(model.getName())
                .phoneNumber(model.getPhoneNumber())
                .role(model.getRole())
                .build();
    }
}
