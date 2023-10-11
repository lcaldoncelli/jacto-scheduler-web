package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.entity.AddressEntity;
import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import br.com.jacto.schedulerservice.exceptions.InvalidDateSchedulerException;
import br.com.jacto.schedulerservice.exceptions.InvalidUserSchedulerException;
import br.com.jacto.schedulerservice.exceptions.SchedulerException;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.repository.VisitScheduleRepository;


import br.com.jacto.schedulerservice.utils.LanguageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class SchedulerServiceTests {
    @InjectMocks
    SchedulerService schedulerService;

    @Mock
    VisitScheduleRepository visitScheduleRepository;

    static AddressEntity address01 = AddressEntity.builder()
            .id(1)
            .city("Pompeia")
            .state("SP")
            .street("Rua Dr. Luiz Miranda")
            .number(1650)
            .complement("Matriz")
            .district("Piraja")
            .postalCode(17580000)
            .build();
    static VisitScheduleEntity visitSchedule01 = VisitScheduleEntity.builder()
            .id(1)
            .userId(1)
            .creationDate(LocalDateTime.of(2030, 1, 31, 23, 55, 17))
            .serviceDescription("Long Description")
            .startDate(LocalDateTime.of(2030, 2, 1, 10, 0, 0))
            .endDate(LocalDateTime.of(2030, 2, 1, 10, 30, 0))
            .address(address01)
            .build();

    static AddressEntity address02 = AddressEntity.builder()
            .id(2)
            .city("Sao Paulo")
            .state("SP")
            .street("Rua Luis Molina")
            .number(12)
            .complement("Ap 123")
            .district("Chacara Klabin")
            .postalCode(4116280)
            .build();
    static VisitScheduleEntity visitSchedule02 = VisitScheduleEntity.builder()
            .id(2)
            .userId(1)
            .creationDate(LocalDateTime.of(2030, 5, 31, 23, 55, 17))
            .serviceDescription("Long Description 2")
            .startDate(LocalDateTime.of(2030, 6, 1, 10, 0, 0))
            .endDate(LocalDateTime.of(2030, 6, 1, 10, 30, 0))
            .address(address02)
            .build();

    @BeforeEach
    public void setUp() {
        when(visitScheduleRepository.findByUser(Mockito.anyLong()))
                .thenReturn(Stream.of(visitSchedule01, visitSchedule02)
                        .collect(Collectors.toCollection(ArrayList::new)));
        when(visitScheduleRepository.save(Mockito.any()))
                .thenReturn(visitSchedule01);
    }

    @Test
    public void getSchedulesTest() {
        var result = schedulerService.getSchedules(1);
        assertEquals(2,result.size());
        assertEquals(VisitScheduleModel.toModel(visitSchedule01), result.get(0));
        assertEquals(VisitScheduleModel.toModel(visitSchedule02), result.get(1));
    }

    @Test
    public void createScheduleTest() throws SchedulerException {
        var result = schedulerService.create(VisitScheduleModel.toModel(visitSchedule01), 1);
        assertNotNull(result);
        assertEquals(VisitScheduleModel.toModel(visitSchedule01), result);
    }

    @Test
    public void createPastScheduleTest() throws SchedulerException {
        VisitScheduleModel model = VisitScheduleModel.toModel(visitSchedule01);
        model.setStartDate(LocalDateTime.now().plusYears(-1));
        Exception exception = assertThrows(InvalidDateSchedulerException.class, () -> {
            schedulerService.create(model, 1);
        });
        assertTrue(exception.getMessage().contains(LanguageUtils.INVALID_DATE));
    }

    @Test
    public void updateScheduleTest() throws SchedulerException {
        var result = schedulerService.update(VisitScheduleModel.toModel(visitSchedule01), 1);
        assertNotNull(result);
        assertEquals(VisitScheduleModel.toModel(visitSchedule01), result);
    }

    @Test
    public void updateScheduleInvalidUserTest() {
        Exception exception = assertThrows(InvalidUserSchedulerException.class, () -> {
            schedulerService.update(VisitScheduleModel.toModel(visitSchedule01), 2);
        });
        assertTrue(exception.getMessage().contains(LanguageUtils.INVALID_USER));
    }

    @Test
    public void updatePastScheduleTest() {
        VisitScheduleModel model = VisitScheduleModel.toModel(visitSchedule01);
        model.setStartDate(LocalDateTime.now().plusYears(-1));
        Exception exception = assertThrows(InvalidDateSchedulerException.class, () -> {
            schedulerService.update(model, 1);
        });
        assertTrue(exception.getMessage().contains(LanguageUtils.INVALID_DATE));
    }


    @Test
    public void deleteScheduleTest() throws SchedulerException {
        schedulerService.delete(VisitScheduleModel.toModel(visitSchedule01), 1);
    }

    @Test
    public void deleteScheduleInvalidUserTest() {
        Exception exception = assertThrows(InvalidUserSchedulerException.class, () -> {
            schedulerService.delete(VisitScheduleModel.toModel(visitSchedule01), 2);
        });
        assertTrue(exception.getMessage().contains(LanguageUtils.INVALID_USER));
    }

}
