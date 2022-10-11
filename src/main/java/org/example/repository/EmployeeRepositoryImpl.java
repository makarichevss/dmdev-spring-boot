package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.entity.EmployeeEntity;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findCustomQuery() {
        return Collections.emptyList();
    }
}
