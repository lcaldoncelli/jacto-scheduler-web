package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import br.com.jacto.schedulerservice.exceptions.InvalidDateSchedulerException;
import br.com.jacto.schedulerservice.exceptions.SchedulerException;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.repository.VisitScheduleRepository;
import br.com.jacto.schedulerservice.utils.SchedulerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    @Autowired
    VisitScheduleRepository visitScheduleRepository;

    /**
     * Return the list of schedules for a specific user
     * @return List<VisitScheduleModel>
     */
    public List<VisitScheduleModel> getSchedules() {
        return visitScheduleRepository.findByUser(1).stream()
                .map(VisitScheduleModel::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Create a schedule for a specific user
     * @return VisitScheduleModel - The created Schedule
     */
    public VisitScheduleModel create(VisitScheduleModel model) throws SchedulerException {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        isScheduleValid(entity, true);
        entity.setCreationDate(LocalDateTime.now());
        entity.setModificationDate(LocalDateTime.now());
        return VisitScheduleModel.toModel(visitScheduleRepository.save(entity));
    }

    /**
     * Create a schedule for a specific user
     * @param model - The VisitScheduleModel to be updated
     * @return VisitScheduleModel - The updated Schedule
     */
    public VisitScheduleModel update(VisitScheduleModel model) throws SchedulerException {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        isScheduleValid(entity, false);
        entity.setModificationDate(LocalDateTime.now());
        return VisitScheduleModel.toModel(visitScheduleRepository.save(entity));
    }

    /**
     * Delete a schedule for a specific user
     * @param model - The VisitScheduleModel to be deleted
     */
    public void delete(VisitScheduleModel model) {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        visitScheduleRepository.delete(entity);
    }

    /**
     * Checks if entity has valid data according to Business Rules
     *
     * @param entity   - VisitScheduleEntity object to be checked
     * @param isCreate - Boolean indicating if data is going to be created ou updated
     * @throws SchedulerException - Throws Exception if data is invalid.
     */
    private void isScheduleValid(VisitScheduleEntity entity, boolean isCreate)
            throws SchedulerException {
        if(SchedulerUtils.isInvalidDateTime(entity.getStartDate())
                || (isCreate && SchedulerUtils.isPastDateTime(entity.getStartDate()))) {
            throw new InvalidDateSchedulerException();
        }
    }
}
