package br.com.jacto.schedulerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalVisitModel {
    private long id;
    private LocalDate scheduledStartDate;
    private LocalDate scheduledEndDate;

    private LocalDate startDate;
    private LocalDate endDate;

    private AddressModel address;
    private String details;
}
