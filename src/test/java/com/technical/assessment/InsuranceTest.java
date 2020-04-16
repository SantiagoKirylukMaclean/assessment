package com.technical.assessment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.technical.assessment.BeforeSetUpTest.defaultInsuranceService;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InsuranceTest {




    @DisplayName("Test getInsuranceByUserName")
    @Test
    public void getInsuranceByUserNameTest() {
        assertEquals("Sallia",defaultInsuranceService.getInsuranceByUserName("Sallia").getName());
    }

}
