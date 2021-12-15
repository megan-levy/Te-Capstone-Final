package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.ShoppingList;

import java.util.List;

public interface ShoppingListDAO {

    List<ShoppingList> getListsByGroupId(Long groupId);

    ShoppingList getIndividualList(Long listId);

    ShoppingList findListByUserId(Long userId);


    //Trying to get the lists that have not been claimed. Not sure if this is how.
    String findByListClaimed (String listClaimedBy);

    void create(String listName, String listDescription, Long groupId);

    void updateShoppingList(String listName, String listDescription, Long listId, String listClaimedBy);

    ShoppingList getUserNameByListId(Long listId);

}
