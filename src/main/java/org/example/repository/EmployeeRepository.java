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
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, EmployeeCustomRepository {

    @Query(value = "select e.* from employee e where e.first_name like :firstName", nativeQuery = true)
    Optional<EmployeeEntity> findByFirstNameContainingIgnoreCase(String firstName);

    @Query("select e from EmployeeEntity e where e.firstName = :firstName and e.salary = :salary")
    List<EmployeeEntity> findAllByFirstNameAndSalary(@Param("firstName") String firstName, @Param("salary") Integer salary);

    List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);

    @Query(value = "select " +
            "e.id as id, " +
            "e.first_name || ' ' || e.last_name as fullName " +
            "from employee e " +
            "where e.salary > :salary", nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);
}
