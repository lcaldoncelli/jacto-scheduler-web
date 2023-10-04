package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.model.AddressModel;
import br.com.jacto.schedulerservice.model.TechnicalVisitModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerService {

    public List<TechnicalVisitModel> getSchedules() {
        ArrayList<TechnicalVisitModel> result = new ArrayList<>();
        TechnicalVisitModel tvm0 = TechnicalVisitModel.builder().details("hello 1").id(1)
                .address(AddressModel.builder().street("My street 1").build()).build();
        TechnicalVisitModel tvm1 = TechnicalVisitModel.builder().details("hello 2").id(2)
                .address(AddressModel.builder().street("My street 2").build()).build();
        result.add(tvm0);
        result.add(tvm1);
        return result;
    }
}
