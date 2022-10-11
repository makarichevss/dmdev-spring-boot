package org.example.repository;

import org.example.IntegrationTestBase;
import org.example.entity.EmployeeEntity;
import org.example.projection.EmployeeNameView;
import org.example.projection.EmployeeNativeView;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest extends IntegrationTestBase {

    private static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testGetById() {
        Optional<EmployeeEntity> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
        employee.ifPresent(entity -> assertEquals("Ivan", entity.getFirstName()));
    }

    @Test
    void testFindByFirstName() {
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContainingIgnoreCase("%va%");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomQuery() {
        List<EmployeeEntity> employees = employeeRepository.findCustomQuery();
        assertThat(employees, hasSize(0));
    }
}