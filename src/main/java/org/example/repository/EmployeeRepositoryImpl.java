package org.example.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeFilter;
import org.example.entity.EmployeeEntity;

import javax.persistence.EntityManager;
import java.util.List;

import static org.example.entity.QEmployeeEntity.employeeEntity;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findByFilter(EmployeeFilter filter) {
        return new JPAQuery<EmployeeEntity>(entityManager)
                .select(employeeEntity)
                .from(employeeEntity)
                .where(employeeEntity.firstName.containsIgnoreCase(filter.getFirstName()))
                .fetch();
    }
}
