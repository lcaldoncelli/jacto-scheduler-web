package br.com.jacto.schedulerservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "VISIT_SCHEDULE")
@SequenceGenerator(name = "TECH_VISIT_SCHEDULE_SEQ", sequenceName = "TECH_VISIT_SCHEDULE_SEQ", initialValue = 1, allocationSize = 1)
public class VisitScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TECH_VISIT_SCHEDULE_SEQ")
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity schedulerUser;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "MODIFICATION_DATE")
    private LocalDateTime modificationDate;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "CONCLUSION_DATE")
    private LocalDateTime conclusionDate;

    @Column(name = "SERVICE_DESCRIPTION")
    private String serviceDescription;

    @Column(name = "CONCLUSION_DESCRIPTION")
    private String conclusionDescription;
}
