package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.model.AddressModel;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerService {

    public List<VisitScheduleModel> getSchedules() {
        ArrayList<VisitScheduleModel> result = new ArrayList<>();
        VisitScheduleModel tvm0 = VisitScheduleModel.builder().details("hello 1").id(1)
                .address(AddressModel.builder().street("My street 1").build()).build();
        VisitScheduleModel tvm1 = VisitScheduleModel.builder().details("hello 2").id(2)
                .address(AddressModel.builder().street("My street 2").build()).build();
        result.add(tvm0);
        result.add(tvm1);
        return result;
    }
}
