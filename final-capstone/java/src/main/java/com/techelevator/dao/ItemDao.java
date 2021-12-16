package com.techelevator.dao;

import com.techelevator.model.Item;

import java.util.Date;
import java.util.List;

public interface ItemDao {

   List<Item> getItemsByListId(Long listId);

   Item getItemByName (String itemName);

   Item getItemByDateAdded(Date dateAdded);

   Item getItemByCoupon(Boolean haveCoupon);

   Item getItemByFavorite(Boolean isFavorite);

   Item getItemByListId (String item);

   long getItemCount (long listId);

   void create(String name, int itemAmount, int itemId, int userId);

   void delete(Long itemId);

   Item getItemByItemId(Long itemId);

   void updateItem(Long itemId, String itemName, int itemAmount, Boolean favorite, Long userId);

   //Decide if we want to include Coupon and Favorite Item when created or is it done with user input once Item created


}
