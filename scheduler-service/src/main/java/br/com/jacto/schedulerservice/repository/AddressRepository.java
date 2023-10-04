package br.com.jacto.schedulerservice.repository;

import br.com.jacto.schedulerservice.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
