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
import io.swagger.models.auth.In;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest

@Suite.SuiteClasses({ BeforeSetUpTest.class, Claim.class, Insurance.class })
public class BeforeSetUpTest {


    @InjectMocks
    static DefaultInsuranceService defaultInsuranceService;

    @InjectMocks
    static DefaultClaimService defaultClaimService;

    @Mock
    private static InsuranceRepository insuranceRepository;

    @Mock
    private static UserServiceInterface userServiceInterface;

    @Mock
    private static ClaimRepository claimRepository;

    @Mock
    private static UserRepository userRepository;


    @BeforeClass
    public static void setUp() {

        List<Insurance> insurances = setInsurances();
        List<Policy> policies = setPolicies(insurances);
        List<Claim> claims = setClaims(policies);
        List<User> users = setUsers(insurances);

        when(insuranceRepository.findAll()).thenReturn(insurances);
        when(claimRepository.findAll()).thenReturn(claims);
        when(userServiceInterface.getUserByUserName("Sallia")).thenReturn(Optional.of(users.get(0)));
        when(userRepository.findByUsername("Sallia")).thenReturn(Optional.of(users.get(0)));

    }

    private static List<Claim> setClaims(List<Policy> policies) {

        List<Claim> claims = new ArrayList<>();
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setPolicyGuilty(policies.get(0));
        claim.setPolicyVictim(policies.get(1));
        claims.add(claim);

        return claims;
    }

    private void setCompensations() {

    }

    private static List<Insurance> setInsurances() {
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
        return insurances;
    }

    private static List<Policy> setPolicies(List<Insurance> insurances) {
        List<Policy> policies = new ArrayList<>();

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy2L = new Policy();//
        policy2L.setId(2L);//
        policy2L.setPolicieDescription("Car");//
        policy2L.setCoveredName("Sergio");//
        policy2L.setCoveredLastName("Almirón");//
        policy2L.setCoveredEmail("sergio@Almiron");//
        policy2L.setInsurance(insurances.get(0));//

        /*

        Policy policy3L = new Policy();//
        policy3L.setId(3L);//
        policy3L.setPolicieDescription("House");//
        policy3L.setCoveredName("Sebastina");//
        policy3L.setCoveredLastName("Frutos");//
        policy3L.setCoveredEmail("sebastian@frutos");//
        policy3L.setInsurance(insurances.get(0));//

        Policy policy4L = new Policy();//
        policy4L.setId(1L);//
        policy4L.setPolicieDescription("House");//
        policy4L.setCoveredName("Sebastina");//
        policy4L.setCoveredLastName("Frutos");//
        policy4L.setCoveredEmail("sebastian@frutos");//
        policy4L.setInsurance(insurances.get(0));//

        Policy policy5L = new Policy();//
        policy5L.setId(1L);//
        policy5L.setPolicieDescription("House");//
        policy5L.setCoveredName("Sebastina");//
        policy5L.setCoveredLastName("Frutos");//
        policy5L.setCoveredEmail("sebastian@frutos");//
        policy5L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

        Policy policy1L = new Policy();//
        policy1L.setId(1L);//
        policy1L.setPolicieDescription("House");//
        policy1L.setCoveredName("Sebastina");//
        policy1L.setCoveredLastName("Frutos");//
        policy1L.setCoveredEmail("sebastian@frutos");//
        policy1L.setInsurance(insurances.get(0));//

         */

        policies.add(policy1L);
        policies.add(policy2L);
        return policies;
    }

    private static List<User> setUsers(List<Insurance> insurances) {
        List<User> users = new ArrayList<>();

        User user = new User();

        user.setId(1L);
        user.setUsername("john");
        user.setEmail("john@nash.com");
        user.setName("John");
        user.setLastName("Nash");
        user.setInsurance(insurances.get(0));
        Optional<User> usero = Optional.of(user);

        users.add(user);

        return users;
    }


}
