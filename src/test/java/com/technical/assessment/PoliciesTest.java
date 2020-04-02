package com.technical.assessment;

import com.technical.assessment.model.Policy;
import com.technical.assessment.repository.PolicyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PoliciesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PolicyRepository policyRepository;

    /*
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Policy policy = new Policy();
        policy.setCoveredEmail("");
        policy.setCoveredName("");
        policy.setCoveredLastName("");
        policy.setPolicieDescription("");

        entityManager.persist(alex);
        entityManager.flush();

        // when
        Employee found = employeeRepository.findByName(alex.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(alex.getName());
    }
     */
}
