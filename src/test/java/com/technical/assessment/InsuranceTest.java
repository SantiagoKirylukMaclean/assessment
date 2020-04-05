package com.technical.assessment;

import com.technical.assessment.model.Insurance;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.service.impl.DefaultInsuranceService;
import io.swagger.models.auth.In;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.h2.expression.function.Function.VALUES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InsuranceTest {

    @InjectMocks
    DefaultInsuranceService defaultInsuranceService = new DefaultInsuranceService();

    @Mock
    private InsuranceRepository insuranceRepository;

    @BeforeEach
    public void setUp() {

        Insurance insuranceSallia = new Insurance();
        List<Insurance> insurances = new ArrayList<>();
        insuranceSallia.setName("Sallia");
        insuranceSallia.setAddress("Gran via 123");
        insuranceSallia.setModifyDateTime(Calendar.getInstance());
        insuranceSallia.setAutomaticAcceptAmount(50000.00);
        insurances.add(insuranceSallia);
        when(insuranceRepository.findAll()).thenReturn(insurances);
    }
    @Test
    public void TrackingNullWeightTest() {

        assertEquals(1,defaultInsuranceService.getInsuranceByUserName("Sallia"));
    }
}
