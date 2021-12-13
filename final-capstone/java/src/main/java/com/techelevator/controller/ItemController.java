package com.techelevator.controller;

import com.techelevator.dao.ItemDao;
import com.techelevator.dao.ShoppingListDAO;
import com.techelevator.model.Item;
import com.techelevator.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ItemController {

    private ItemDao itemDao;
    private ShoppingListDAO shoppingListDao;

    public ItemController(ItemDao itemDao, ShoppingListDAO shoppingListDao) {
        this.itemDao= itemDao;
        this.shoppingListDao = shoppingListDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value= "/lists/{listId}/items", method = RequestMethod.POST)
    public void createItem(@RequestParam String listId, @Valid @RequestBody Item newItem){

        itemDao.create(newItem.getItemName(), newItem.getItemAmount(), Integer.parseInt(listId));
    }

    @RequestMapping(value= "/lists/{listId}/items/count", method = RequestMethod.GET)
    public int getItemCount(@RequestParam String listId){

       return itemDao.getItemCount(Integer.parseInt(listId));
    }
}
