package org.example.repository;

import org.example.dto.EmployeeFilter;
import org.example.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCustomRepository {

    List<EmployeeEntity> findByFilter(EmployeeFilter filter);
}
