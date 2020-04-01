package com.technical.assessment.repository;

import com.technical.assessment.model.Policie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policie,Long> {
}
