package org.example.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.example.IntegrationTestBase;
import org.example.dto.EmployeeFilter;
import org.example.entity.EmployeeEntity;
import org.example.entity.QEmployeeEntity;
import org.example.projection.EmployeeNameView;
import org.example.projection.EmployeeNativeView;
import org.example.util.QPredicates;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.example.entity.QEmployeeEntity.*;
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
        EmployeeFilter filter = EmployeeFilter
                .builder()
                .firstName("ivaN")
                .build();
        List<EmployeeEntity> employees = employeeRepository.findByFilter(filter);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testQuerydslPredicates() {
        BooleanExpression predicate =
                employeeEntity.firstName.containsIgnoreCase("ivaN")
                        .and(employeeEntity.salary.goe(1000));
        Page<EmployeeEntity> allValues = employeeRepository.findAll((Pageable) predicate);
        assertThat(allValues.getContent(), hasSize(1));
    }

    @Test
    void testQPredicates() {
        EmployeeFilter filter = EmployeeFilter
                .builder()
                .firstName("ivaN")
                .salary(1000)
                .build();
        Predicate predicate = QPredicates.builder()
                .add(filter.getFirstName(), employeeEntity.firstName::containsIgnoreCase)
                .add(filter.getLastName(), employeeEntity.lastName::containsIgnoreCase)
                .add(filter.getSalary(), employeeEntity.salary::goe)
                .buildAnd();
        List<EmployeeEntity> result = employeeRepository.findAll((Sort) predicate);
        assertTrue(result.iterator().hasNext());
    }
}