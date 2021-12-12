package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.ShoppingList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcShoppingListDao implements ShoppingListDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcShoppingListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ShoppingList> getListsByGroupId(Long groupId) {
        List<ShoppingList> shoppingList = new ArrayList<>();
        String sql = "SELECT * FROM lists WHERE lists.group_id = ? ORDER BY lists.list_name ASC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupId);

        while (results.next()) {
            shoppingList.add(mapRowToList(results));
        }

        return shoppingList;
    }

    @Override
    public ShoppingList getIndividualList(Long listId) {
        ShoppingList shoppingList = null;
        String sql = "SELECT list_id, list_name, group_id, list_claimed, list_description FROM lists WHERE lists.list_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);
        if (results.next()) {
            shoppingList = mapRowToList(results);
        }

        return shoppingList;
//        return jdbcTemplate.queryForObject(sql, ShoppingList.class, listId);
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
    public void create(String listName, String listDescription, Long groupId) {
        String insertList ="INSERT INTO lists(list_name, list_description, group_id, list_claimed)" +
                " VALUES(?,?,?,?)";
        jdbcTemplate.update(insertList, listName, listDescription, groupId, false);
    }

    private ShoppingList mapRowToList(SqlRowSet rs) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setListId(rs.getLong("list_id"));
        shoppingList.setGroupId(rs.getLong("group_id"));
        shoppingList.setListName(rs.getString("list_name"));
        shoppingList.setListDescription(rs.getString("list_description"));
        shoppingList.setListClaimed(rs.getBoolean("list_claimed"));
        return shoppingList;
    }

}
