package org.example.repository;

import org.example.IntegrationTestBase;
import org.example.entity.CompanyEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Integer APPLE_ID = 1;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetById() {
        Optional<CompanyEntity> company = companyRepository.findById(APPLE_ID);
        assertTrue(company.isPresent());
        company.ifPresent(entity -> assertEquals("Apple", entity.getCompany_name()));
    }

    @Test
    void testSave() {
        CompanyEntity company = CompanyEntity.builder()
                .company_name("Fitbit")
                .build();
        companyRepository.save(company);
        assertNotNull(company.getId());
    }
}