package org.example.repository;

import org.example.domain.User.HospitalManagerEntity;
import org.example.domain.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalManagerRepository extends JpaRepository<HospitalManagerEntity,Long> {
    Optional<HospitalManagerEntity> findByUser(UserEntity user);
}
