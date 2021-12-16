package com.techelevator.dao;

import com.techelevator.model.ShoppingList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcShoppingListDao implements ShoppingListDAO {

    private JdbcTemplate jdbcTemplate;
    private ItemDao itemDao;
    private UserDao userDao;

    public JdbcShoppingListDao(JdbcTemplate jdbcTemplate, ItemDao itemDao, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.itemDao = itemDao;
        this.userDao = userDao;
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
        String sql = "SELECT * FROM lists WHERE lists.list_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);
        if (results.next()) {
            shoppingList = mapRowToList(results);
        }
        return shoppingList;
    }

    @Override
    public ShoppingList getUserNameByListId(Long listId) {

        return jdbcTemplate.queryForObject("SELECT username FROM users \n" +
                "JOIN member_of ON users.user_id = member_of.user_id\n" +
                "JOIN groups ON groups.group_id = member_of.group_id\n" +
                "JOIN lists ON groups.group_id = lists.group_id\n" +
                "WHERE list_id = ?", ShoppingList.class, listId);
//        ShoppingList shoppingList = null;
//        String sql ="SELECT username FROM users \n" +
//                "JOIN member_of ON users.user_id = member_of.user_id\n" +
//                "JOIN groups ON groups.group_id = member_of.group_id\n" +
//                "JOIN lists ON groups.group_id = lists.group_id\n" +
//                "WHERE list_id = ?";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);
//        if(results.next()){
//            shoppingList = mapRowToList(results);
//        }
//        return shoppingList;
    }

    @Override
    public ShoppingList findListByUserId(Long userId) {
        return null;
    }

    @Override
    public Integer findByListClaimed(Integer listClaimedBy) {
        return listClaimedBy;
    }

    @Override
    public void create(String listName, String listDescription, Long groupId, Long userId) {
        String insertList = "INSERT INTO lists(list_name, list_description, group_id, list_claimed_By)" +
                " VALUES(?,?,?,?)";
        jdbcTemplate.update(insertList, listName, listDescription, groupId, userId);
    }

    @Override
    public void updateShoppingList(Long listId, String listName, String listDescription, Integer listClaimedBy) {
        String updateList = "UPDATE lists SET list_name = ?, list_description = ?, list_claimed_By = ? WHERE list_id = ?";
        if (listClaimedBy == null || listClaimedBy == 0) {
            jdbcTemplate.update(updateList, listName, listDescription, null, listId );
        } else {
            jdbcTemplate.update(updateList, listName, listDescription, listClaimedBy, listId );
        }
    }

    @Override
    public String getUserNameFromUserId(Long listId) {
        if (listId == null) {
            System.out.println("listId is Null.");
            return "";
        }
        return jdbcTemplate.queryForObject("select username FROM users JOIN lists ON lists.list_claimed_by = users.user_id WHERE lists.list_id = ?", String.class, listId);
    }

    @Override
    public void deleteItemsCascade(Long listId){

        // lists are set to cascade delete for items
        // cascade delete requires deleting the parent
        // which is not what we want; just want to
        // delete items

        // The solution is the following sql string.
        // This will temporarily catch the all of the
        // values of the deleted list which we can
        // safely restore once the cascade delete has finished.
        String deleteList =
                "WITH old_list AS ( DELETE FROM lists WHERE list_id = ? RETURNING * )\n" +
                "INSERT INTO lists\n" +
                "SELECT *\n" +
                "FROM old_list;";
        jdbcTemplate.update(deleteList, listId);

        String updateClaim = "UPDATE lists SET list_claimed_By = ? WHERE list_id = ?";
        jdbcTemplate.update(updateClaim, null, listId);


    }


    private ShoppingList mapRowToList(SqlRowSet rs) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setListId(rs.getLong("list_id"));
        shoppingList.setGroupId(rs.getLong("group_id"));
        shoppingList.setListName(rs.getString("list_name"));
        shoppingList.setListDescription(rs.getString("list_description"));
        shoppingList.setListClaimedBy(rs.getInt("list_claimed_by"));
        shoppingList.setListClaimed(shoppingList.getListClaimedBy() != null && shoppingList.getListClaimedBy() != 0);
        shoppingList.setItemCount(itemDao.getItemCount((long) shoppingList.getListId()));
        if (shoppingList.getListClaimedBy() != null && shoppingList.getListClaimedBy() != 0) {
            shoppingList.setClaimedByName(this.getUserNameFromUserId(shoppingList.getListId()));
        } else {
            shoppingList.setClaimedByName(null);
        }
        return shoppingList;
    }

}
