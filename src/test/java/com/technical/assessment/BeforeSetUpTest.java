package com.technical.assessment;

import com.technical.assessment.model.Claim;
import com.technical.assessment.model.Insurance;
import com.technical.assessment.model.Policy;
import com.technical.assessment.model.User;
import com.technical.assessment.repository.ClaimRepository;
import com.technical.assessment.repository.InsuranceRepository;
import com.technical.assessment.repository.UserRepository;
import com.technical.assessment.service.UserServiceInterface;
import com.technical.assessment.service.impl.DefaultClaimService;
import com.technical.assessment.service.impl.DefaultInsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


public class BeforeSetUpTest {

    @InjectMocks
    DefaultInsuranceService defaultInsuranceService;

    @InjectMocks
    DefaultClaimService defaultClaimService;

    @Mock
    InsuranceRepository insuranceRepository;

    @Mock
    UserServiceInterface userServiceInterface;

    @Mock
    ClaimRepository claimRepository;

    @Mock
    UserRepository userRepository;


    @BeforeEach
    public void setUp() {

        List<Claim> claims = new ArrayList<>();//
        Claim claim = new Claim();//

        Policy policyGuilty = new Policy();//
        Policy policyVictim = new Policy();//

        List<Insurance> insurances = new ArrayList<>();//

        Insurance insuranceSallia = new Insurance();//
        insuranceSallia.setId(1L);//
        insuranceSallia.setName("Sallia");//
        insuranceSallia.setAddress("Avinguda Diagonal 123");//
        insuranceSallia.setModifyDateTime(Calendar.getInstance());//
        insuranceSallia.setAutomaticAcceptAmount(50000.00);//

        Insurance insuranceRotular = new Insurance();//
        insuranceRotular.setId(2L);//
        insuranceRotular.setName("Rotular");//
        insuranceRotular.setAddress("Gran via 123");//
        insuranceRotular.setModifyDateTime(Calendar.getInstance());//
        insuranceRotular.setAutomaticAcceptAmount(45000.00);//

        Insurance insuranceXenix = new Insurance();
        insuranceXenix.setId(3L);
        insuranceXenix.setName("Xenix");
        insuranceXenix.setAddress("Marina 123");
        insuranceXenix.setModifyDateTime(Calendar.getInstance());
        insuranceXenix.setAutomaticAcceptAmount(40000.00);

        insurances.add(insuranceSallia);
        insurances.add(insuranceRotular);
        insurances.add(insuranceXenix);

        when(insuranceRepository.findAll()).thenReturn(insurances);

        policyGuilty.setId(1L);//
        policyGuilty.setPolicieDescription("House");//
        policyGuilty.setCoveredName("Sebastina");//
        policyGuilty.setCoveredLastName("Frutos");//
        policyGuilty.setCoveredEmail("sebastian@frutos");//
        policyGuilty.setInsurance(insuranceSallia);//

        policyVictim.setId(2L);//
        policyVictim.setPolicieDescription("Car");//
        policyVictim.setCoveredName("Sergio");//
        policyVictim.setCoveredLastName("Almirón");//
        policyVictim.setCoveredEmail("sergio@Almiron");//
        policyVictim.setInsurance(insuranceRotular);//

        claim.setId(1L);//
        claim.setPolicyGuilty(policyGuilty);//
        claim.setPolicyVictim(policyVictim);//
        claims.add(claim);//

        when(claimRepository.findAll()).thenReturn(claims);//

        List<User> users = new ArrayList<>();

        User user = new User();

        user.setId(1L);
        user.setUsername("john");
        user.setEmail("john@nash.com");
        user.setName("John");
        user.setLastName("Nash");
        user.setInsurance(insuranceSallia);
        Optional<User> usero = Optional.of(user);

        users.add(user);

        when(userServiceInterface.getUserByUserName("Sallia")).thenReturn(usero);
        when(userRepository.findByUsername("Sallia")).thenReturn(usero);

    }

}
