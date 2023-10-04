package br.com.jacto.schedulerservice.repository;

import br.com.jacto.schedulerservice.entity.VisitScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitScheduleRepository extends JpaRepository<VisitScheduleEntity, Long> {
    @Query(value = "SELECT * FROM VisitScheduleEntity WHERE id = :userId", nativeQuery = true)
    public List<VisitScheduleEntity> findByUser(long userId);

}
