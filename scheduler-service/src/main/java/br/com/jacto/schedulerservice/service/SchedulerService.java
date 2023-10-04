package br.com.jacto.schedulerservice.service;

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
        ArrayList<VisitScheduleModel> result = new ArrayList<>();
        return visitScheduleRepository.findByUser(1).stream()
                .map(VisitScheduleModel::toModel)
                .collect(Collectors.toList());
    }
}
