package org.example.repository;

import org.example.entity.EmployeeEntity;
import org.example.projection.EmployeeNameView;
import org.example.projection.EmployeeNativeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeCustomRepository {

    List<EmployeeEntity> findCustomQuery();
}
