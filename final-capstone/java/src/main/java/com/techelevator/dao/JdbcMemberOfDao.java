package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

//@Service
//public class JdbcMemberOfDao implements JdbcMemberOfDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public JdbcMemberOfDao(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public Long create(Long userId) {
//        String createUser = "INSERT INTO memberOf(user_id)" +
//                " SELECT user_id FROM users" +
//                " VALUES(?,?) RETURNING group_id";
//        Long newGroupId = jdbcTemplate.queryForObject(createUser, Long.class, userId );
//
//        return newGroupId;
//    }
//}
