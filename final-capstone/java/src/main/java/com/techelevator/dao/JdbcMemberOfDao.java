package com.techelevator.dao;

import com.techelevator.model.RandomDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcMemberOfDao  {

    private JdbcTemplate jdbcTemplate;


    public JdbcMemberOfDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }



}
