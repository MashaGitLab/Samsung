package com.maria.web_access.domain.service;

import com.maria.web_access.domain.entity.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Journal> getLatestRecords() {
        List<Journal> records = jdbcTemplate.query(
                "SELECT * FROM journal ORDER BY id DESC LIMIT 100",
                (rs, rowNum) -> {
                    Journal entity = new Journal();
                    entity.setId(rs.getLong("id"));
                    entity.setComment(rs.getString("comment"));
                    entity.setTime(rs.getTimestamp("time"));
                    // Продолжайте получать значения других полей из результирующего набора
                    return entity;
                });

        return records;
    }
}
