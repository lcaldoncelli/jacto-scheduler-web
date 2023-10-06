package br.com.jacto.authservice.model;

import br.com.jacto.authservice.entity.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserModel {
    private String email;
    private String name;
    private String phoneNumber;
    private UserRole role;
    private String password;

    public static CreateUserModel toModel(UserEntity entity) {
        return CreateUserModel.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .build();
    }

    public static LoginModel toLoginModel(CreateUserModel model) {
        return LoginModel.builder()
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }

    public static UserEntity toEntity(CreateUserModel model) {
        return UserEntity.builder()
                .email(model.getEmail())
                .name(model.getName())
                .phoneNumber(model.getPhoneNumber())
                .role(model.getRole())
                .build();
    }

}
