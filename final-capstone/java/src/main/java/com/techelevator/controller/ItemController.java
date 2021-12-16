package com.techelevator.controller;

import com.techelevator.dao.ItemDao;
import com.techelevator.dao.ShoppingListDAO;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Item;
import com.techelevator.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ItemController {

    private ItemDao itemDao;
    private ShoppingListDAO shoppingListDao;
    private UserDao userDao;

    public ItemController(ItemDao itemDao, ShoppingListDAO shoppingListDao, UserDao userDao) {
        this.itemDao= itemDao;
        this.shoppingListDao = shoppingListDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value= "/lists/{listId}/items", method = RequestMethod.POST)
    public void createItem(@RequestParam String listId, @Valid @RequestBody Item newItem, Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        int id = Integer.parseInt(listId);
        itemDao.create(newItem.getItemName(), newItem.getItemAmount(), id, userId);
    }

    @DeleteMapping(path="/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        itemDao.delete(itemId);
    }

    @RequestMapping(value= "/lists/{listId}/items/count", method = RequestMethod.GET)
    public int getItemCount(@RequestParam String listId) {
        long id = Long.parseLong(listId);
        return (int) itemDao.getItemCount(id);
    }
//    updateItem
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.PUT)
    public void updateItem(@RequestBody Item item, @PathVariable Long itemId) {
        if (!item.getListItemId().equals(itemId)) return;
        itemDao.updateItem(itemId, item.getItemName(), item.getItemAmount(), item.getFavorite());
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    public Item getSingleItem(@PathVariable Long itemId) {
        return itemDao.getItemByItemId(itemId);
    }

    @RequestMapping(value= "/lists/{listId}/items", method = RequestMethod.GET)
    public List<Item> itemList(@RequestParam String listId){
        return itemDao.getItemsByListId(Long.parseLong(listId));
    }
}
