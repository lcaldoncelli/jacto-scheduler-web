package br.com.jacto.schedulerservice.entity;

import jakarta.persistence.*;
import lombok.*;
import br.com.jacto.schedulerservice.model.UserRole;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SCHEDULER_USER")
@SequenceGenerator(name = "SCHEDULER_USER_SEQ", sequenceName = "SCHEDULER_USER_SEQ", initialValue = 1, allocationSize = 1)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULER_USER_SEQ")
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMER")
    private String phoneNumber;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "schedulerUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<VisitScheduleEntity> technicalVisitScheduleEntities = new ArrayList<>();
}
