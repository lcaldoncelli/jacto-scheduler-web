package br.com.jacto.authservice.entity;

import jakarta.persistence.*;
import lombok.*;
import br.com.jacto.authservice.model.UserRole;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AUTH_USER")
@SequenceGenerator(name = "AUTH_USER_SEQ", sequenceName = "AUTH_USER_SEQ", initialValue = 1, allocationSize = 1)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_USER_SEQ")
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMER")
    private String phoneNumber;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
