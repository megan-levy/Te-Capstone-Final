package com.techelevator.dao;

import com.techelevator.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JdbcItemDao implements ItemDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> getItemsByListId(Long listId) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE items.list_id= ? ORDER by items.item_name ASC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);

        while (results.next()){
            itemList.add(mapRowToItem(results));
        }
        return itemList;
    }

    @Override
    public Item getItemByName(String itemName) {
        return null;
    }

    @Override
    public Item getItemByDateAdded(Date dateAdded) {
        return null;
    }

    @Override
    public Item getItemByCoupon(Boolean haveCoupon) {
        return null;
    }

    @Override
    public Item getItemByFavorite(Boolean isFavorite) {
        return null;
    }

    @Override
    public Item getItemByListId(String item) {
        return null;
    }

    @Override
    public long getItemCount (long listId){
        String sql ="SELECT COUNT(list_item_id) FROM items WHERE list_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, listId);

        if (count <= 0) {
            return 0;
        }
        return Long.parseLong(String.valueOf(count));
    }

    @Override
    public void create(String name, int itemAmount, int itemId, int userId) {
        // does this need to be in query list_id, date_added, user_id,
                String insertItem ="INSERT INTO items( item_name, item_amount, list_id, favorite, user_id)" +
                " VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertItem, name, itemAmount, itemId, false, userId);
    }

    @Override
    public void updateItem(Long itemId, String itemName, int itemAmount, Boolean favorite, Long userId) {
        String updateItem = "UPDATE items SET " +
                "item_name = ?, " +
                "item_amount = ?, " +
                "user_id = ?, " +
                "favorite = ? " +
                "WHERE list_item_id = ?";
        jdbcTemplate.update(updateItem, itemName, itemAmount, userId, favorite, itemId);
    }

    @Override
    public void delete(Long itemId){
            String deleteItem = "DELETE FROM items WHERE list_item_id = ?";
            jdbcTemplate.update(deleteItem, itemId);
    }

    @Override
    public Item getItemByItemId(Long itemId) {
        Item item = null;
        String getItem = "SELECT * FROM items WHERE list_item_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(getItem, itemId);
        if (results.next()) {
            item = mapRowToItem(results);
        }
        return item;
    }

    private Item mapRowToItem(SqlRowSet rowSet) {
        Item item = new Item();
        item.setListItemId(rowSet.getLong("list_item_id"));
        item.setItemName(rowSet.getString("item_name"));
        item.setItemAmount(rowSet.getInt("item_amount"));
        item.setListId(rowSet.getLong("list_id"));
        item.setDateAdded(rowSet.getDate("date_added"));
        item.setUserId(rowSet.getLong("user_id"));
        item.setFavorite(rowSet.getBoolean("favorite"));
        return item;
    }
}
