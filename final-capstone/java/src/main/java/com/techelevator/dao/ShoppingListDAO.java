package com.techelevator.dao;

import com.techelevator.model.ShoppingList;

public interface ShoppingListDAO {

    ShoppingList getListByGroupId(Long groupId);

    ShoppingList findListByUserId(Long userId);

    //Trying to get the lists that have not been claimed. Not sure if this is how.
    boolean findByListClaimed (Boolean listClaimed);

    Long create(String listName, String listDescription);
}
