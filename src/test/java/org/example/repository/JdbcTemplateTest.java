package org.example.repository;

import org.example.IntegrationTestBase;
import org.example.entity.CompanyEntity;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class JdbcTemplateTest extends IntegrationTestBase {

    private static final String INSERT_SQL =
            "insert into company (company_name) values (?);";
    private static final String DELETE_RETURNING_SQL =
            "delete from company where company_name = ? returning *;";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void testInsert() {
        int res = jdbcOperations.update(INSERT_SQL, "Microsoft");
        Assertions.assertEquals(1, res);
    }

    @Test
    void testReturning() {
        String companyName = "Microsoft";
        jdbcOperations.update(INSERT_SQL, companyName);
        List<CompanyEntity> res = jdbcOperations.query(DELETE_RETURNING_SQL, (ResultSet rs, int rowNum) -> CompanyEntity.builder()
                .id(rs.getInt("id"))
                .company_name(rs.getString("company_name"))
                .build(), companyName);
        MatcherAssert.assertThat(res, hasSize(1));
    }
}