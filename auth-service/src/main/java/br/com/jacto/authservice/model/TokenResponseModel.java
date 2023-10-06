package br.com.jacto.authservice.model;

import br.com.jacto.authservice.entity.UserEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseModel extends UserModel {
    private String token;

    public static TokenResponseModel toModel(UserEntity entity, String token) {
        return TokenResponseModel.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .role(entity.getRole())
                .token(token)
                .build();
    }
}
