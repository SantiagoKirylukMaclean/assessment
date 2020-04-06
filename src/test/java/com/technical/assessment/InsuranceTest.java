package com.technical.assessment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InsuranceTest extends BeforeSetUpTest {


    @DisplayName("Test getInsuranceByUserName")
    @Test
    public void getInsuranceByUserNameTest() {
        assertEquals("Sallia",defaultInsuranceService.getInsuranceByUserName("Sallia").getName());
    }

}
