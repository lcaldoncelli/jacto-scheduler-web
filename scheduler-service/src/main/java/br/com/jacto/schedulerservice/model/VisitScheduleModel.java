package br.com.jacto.schedulerservice.model;

import br.com.jacto.schedulerservice.entity.AddressEntity;
import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitScheduleModel {
    private long id;
    private AddressModel address;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime conclusionDate;
    private String serviceDescription;
    private String conclusionDescription;

    public static VisitScheduleModel toModel(VisitScheduleEntity entity) {
        return VisitScheduleModel.builder()
                .id(entity.getId())
                .address(AddressModel.toModel(entity.getAddress()))
                .creationDate(entity.getCreationDate())
                .modificationDate(entity.getModificationDate())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .conclusionDate(entity.getConclusionDate())
                .serviceDescription(entity.getServiceDescription())
                .conclusionDescription(entity.getConclusionDescription())
                .build();
    }

    public static VisitScheduleEntity toEntity(VisitScheduleModel model) {
        return VisitScheduleEntity.builder()
                .id(model.getId())
                .address(AddressModel.toEntity(model.getAddress()))
                .creationDate(model.getCreationDate())
                .modificationDate(model.getModificationDate())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .conclusionDate(model.getConclusionDate())
                .serviceDescription(model.getServiceDescription())
                .conclusionDescription(model.getConclusionDescription())
                .build();
    }
}
