package com.technical.assessment;

import com.technical.assessment.model.Policy;
import com.technical.assessment.repository.PolicyRepository;
import com.technical.assessment.service.impl.DefaultPolicyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PoliciesTest {

    @Autowired
    private DefaultPolicyService defaultPolicyService;

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testGet() {
        assertEquals("ResponseEntity", defaultPolicyService.getPolicyByUsername("ronald"));
    }
}
