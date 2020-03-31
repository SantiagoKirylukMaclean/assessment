package com.technical.assessment.repository;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findById(Long id);
}
