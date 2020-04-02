package com.technical.assessment.repository;

import com.technical.assessment.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy,Long> {
}
