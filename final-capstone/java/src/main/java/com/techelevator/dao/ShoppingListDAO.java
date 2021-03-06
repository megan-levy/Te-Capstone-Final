package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.ShoppingList;

import java.util.List;

public interface ShoppingListDAO {

    List<ShoppingList> getListsByGroupId(Long groupId);

    ShoppingList getIndividualList(Long listId);

    ShoppingList findListByUserId(Long userId);

    void deleteItemsCascade(Long listId);

    //Trying to get the lists that have not been claimed. Not sure if this is how.
    Integer findByListClaimed (Integer listClaimedBy);

    void create(String listName, String listDescription, Long groupId, Long userId);

    void updateShoppingList(Long listId, String listName, String listDescription, Integer listClaimedBy);

    ShoppingList getUserNameByListId(Long listId);

    String getUserNameFromUserId ( Long listId);

}
