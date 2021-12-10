package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ShoppingListDAO;
import com.techelevator.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class ShoppingListController {

    private ShoppingListDAO shoppingListDAO;
    private GroupDao groupDao;
    //private ShoppingList shoppingList;

    public ShoppingListController(ShoppingListDAO shoppingListDAO, GroupDao groupDao) {
        this.shoppingListDAO = shoppingListDAO;
        this.groupDao = groupDao;
        //this.shoppingList = shoppingList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}/lists", method = RequestMethod.POST)
    public void createShoppingList(@RequestParam String groupId, @Valid @RequestBody ShoppingListDAO newList) {

        shoppingListDAO.create(shoppingList.getListName(), shoppingList.getListDescription(), Long.parseLong(groupId));
    }
}



