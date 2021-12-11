package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.ShoppingList;

import java.util.List;

public interface ShoppingListDAO {

    List<ShoppingList> getListsByGroupId(Long groupId);

    ShoppingList getListByGroupId(Long groupId);

    ShoppingList findListByUserId(Long userId);

    //Trying to get the lists that have not been claimed. Not sure if this is how.
    boolean findByListClaimed (Boolean listClaimed);

    void create(String listName, String listDescription, Long groupId);
}
