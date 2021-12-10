package com.techelevator.dao;

import com.techelevator.model.ShoppingList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcShoppingListDao implements ShoppingListDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcShoppingListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ShoppingList getListByGroupId(Long groupId) {
        return null;
    }

    @Override
    public ShoppingList findListByUserId(Long userId) {
        return null;
    }

    @Override
    public boolean findByListClaimed(Boolean listClaimed) {
        return false;
    }

    @Override
    public Long create(String listName, String listDescription) {
        String insertList ="INSERT INTO lists(list_name, list_description)" +
                " VALUES(?,?) RETURNING list_id;";
        Long newListId = jdbcTemplate.queryForObject(insertList, Long.class, listName, listDescription);

        return newListId;
    }

}
