package br.com.jacto.schedulerservice.service;

import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import br.com.jacto.schedulerservice.exceptions.InvalidDateSchedulerException;
import br.com.jacto.schedulerservice.exceptions.InvalidUserSchedulerException;
import br.com.jacto.schedulerservice.exceptions.ScheduleNotFoundException;
import br.com.jacto.schedulerservice.exceptions.SchedulerException;
import br.com.jacto.schedulerservice.model.VisitScheduleModel;
import br.com.jacto.schedulerservice.repository.VisitScheduleRepository;
import br.com.jacto.schedulerservice.utils.SchedulerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    @Autowired
    VisitScheduleRepository visitScheduleRepository;

    /**
     * Return the list of schedules for a specific user
     * @param userId - Authenticated User Id
     * @return List<VisitScheduleModel>
     */
    public List<VisitScheduleModel> getSchedules(long userId) {
        return visitScheduleRepository.findByUser(userId).stream()
                .map(VisitScheduleModel::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Read a schedule for a specific user
     * @param scheduleId - The VisitScheduleId to be fetched
     * @param userId - Authenticated User Id
     * @return VisitScheduleModel
     */
    public VisitScheduleModel read(long scheduleId, long userId) throws SchedulerException {
        Optional<VisitScheduleEntity> entity = visitScheduleRepository.findById(scheduleId);
        if (entity.isEmpty()) throw new ScheduleNotFoundException();
        isUserIdValid(entity.get().getUserId(), userId);
        return VisitScheduleModel.toModel(entity.get());
    }

    /**
     * Create a schedule for a specific user
     * @param userId - Authenticated User Id
     * @return VisitScheduleModel - The created Schedule
     */
    public VisitScheduleModel create(VisitScheduleModel model, long userId) throws SchedulerException {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        isScheduleValid(entity);
        entity.setCreationDate(LocalDateTime.now());
        entity.setModificationDate(LocalDateTime.now());
        entity.setUserId(userId);

        return VisitScheduleModel.toModel(visitScheduleRepository.save(entity));

    }

    /**
     * Create a schedule for a specific user
     * @param model - The VisitScheduleModel to be updated
     * @param userId - Authenticated User Id
     * @return VisitScheduleModel - The updated Schedule
     */
    public VisitScheduleModel update(VisitScheduleModel model, long userId) throws SchedulerException {
        VisitScheduleEntity entity = VisitScheduleModel.toEntity(model);
        isScheduleValid(entity);
        isUserIdValid(model.getUserId(), userId);
        entity.setModificationDate(LocalDateTime.now());
        return VisitScheduleModel.toModel(visitScheduleRepository.save(entity));
    }

    /**
     * Delete a schedule for a specific user
     * @param scheduleId - The VisitScheduleId to be deleted
     * @param userId - Authenticated User Id
     */
    public void delete(long scheduleId, long userId) throws SchedulerException {
        Optional<VisitScheduleEntity> entity = visitScheduleRepository.findById(scheduleId);
        if (entity.isEmpty()) throw new ScheduleNotFoundException();
        isUserIdValid(entity.get().getUserId(), userId);
        visitScheduleRepository.delete(entity.get());
    }

    /**
     * Checks if entity has valid data according to Business Rules
     *
     * @param entity   - VisitScheduleEntity object to be checked
     * @throws SchedulerException - Throws Exception if data is invalid.
     */
    private void isScheduleValid(VisitScheduleEntity entity)
            throws SchedulerException {
        if(SchedulerUtils.isInvalidDateTime(entity.getStartDate())
                || SchedulerUtils.isPastDateTime(entity.getStartDate())) {
            throw new InvalidDateSchedulerException();
        }
    }

    /**
     * Checks if entity has valid user ID in comparison with the authenticated User
     *
     * @param userId - User ID
     * @param authenticatedUserId - Authenticated User ID
     * @throws SchedulerException - Throws Exception if data is invalid.
     */
    private void isUserIdValid(long userId, long authenticatedUserId)
            throws SchedulerException {
        if(authenticatedUserId != userId) {
            throw new InvalidUserSchedulerException();
        }
    }
}
