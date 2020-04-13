package com.technical.assessment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClaimTest extends BeforeSetUpTest {

    @DisplayName("Test getClaimsGultyByUserName")
    @Test
    public void getClaimsGultyByUserName() {
        assertEquals(1,defaultClaimService.getClaimsGuiltyByUserName("Sallia").size());
    }

    @DisplayName("Test getClaimsVictimByUserName")
    @Test
    public void getClaimsVictimByUserName() {
        assertEquals(0,defaultClaimService.getClaimsVictimByUserName("Sallia").size());
    }

}
