package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import br.com.jacto.schedulerservice.model.AddressModel;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.repository.VisitScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    @Autowired
    VisitScheduleRepository visitScheduleRepository;

    public List<VisitScheduleModel> getSchedules() {
        return visitScheduleRepository.findByUser(1).stream()
                .map(VisitScheduleModel::toModel)
                .collect(Collectors.toList());
    }

    public VisitScheduleModel create(VisitScheduleModel model) {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        return VisitScheduleModel.toModel(visitScheduleRepository.save(entity));
    }

    public VisitScheduleModel update(VisitScheduleModel model) {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        return VisitScheduleModel.toModel(visitScheduleRepository.save(entity));
    }

    public void delete(VisitScheduleModel model) {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        visitScheduleRepository.delete(entity);
    }
}
