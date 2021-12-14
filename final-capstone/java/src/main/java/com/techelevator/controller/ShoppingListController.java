package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ShoppingListDAO;
import com.techelevator.model.Group;
import com.techelevator.model.ShoppingList;
import com.techelevator.model.ShoppingListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public void createShoppingList(@RequestParam String groupId, @Valid @RequestBody ShoppingListDTO newList) {

        shoppingListDAO.create(newList.getListName(), newList.getListDescription(), Long.parseLong(groupId));
    }

    @RequestMapping(value = "/group/{groupId}/lists", method = RequestMethod.GET)
    public List<ShoppingList> findAll(@RequestParam String groupId) {
        return shoppingListDAO.getListsByGroupId(Long.parseLong(groupId));
    }

    @RequestMapping(value = "/lists/{listId}", method = RequestMethod.GET)
    public ShoppingList getSingleList(@RequestParam String listId) {
        return shoppingListDAO.getIndividualList(Long.parseLong(listId));
    }

    @RequestMapping(value = "/lists/", method = RequestMethod.GET)
    public ShoppingList getUserNameByListId(@RequestParam Long listId){
        return shoppingListDAO.getUserNameByListId(listId);
    }
}



