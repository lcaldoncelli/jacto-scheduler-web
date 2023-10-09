package br.com.jacto.authservice.repository;

import br.com.jacto.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);
    @Query(value = "SELECT * FROM AUTH_USER WHERE EMAIL = :username", nativeQuery = true)
    public Optional<UserEntity> findByUsername(String username);
}
