package com.techelevator.dao;

import com.techelevator.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.awt.event.ItemListener;
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
            itemList.add(mapRowToList(results));
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
    public int getItemCount (int listId){
        String count ="SELECT COUNT(list_id) FROM lists WHERE list_id = ?";
        return Integer.parseInt(count);
    }

    @Override
    public void create(String name, int itemAmount, int itemId) {
        // does this need to be in query list_id, date_added, user_id,
                String insertItem ="INSERT INTO items(list_item_id, item_name, item_amount, list_id, user_id, favorite" +
                " VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertItem, name, itemAmount, itemId);
    }

    private Item mapRowToList(SqlRowSet rowSet) {
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
