package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ShoppingListDAO;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Group;
import com.techelevator.model.ShoppingList;
import com.techelevator.model.ShoppingListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ShoppingListController {

    private ShoppingListDAO shoppingListDAO;
    private GroupDao groupDao;
    private UserDao userDao;
    //private ShoppingList shoppingList;

    public ShoppingListController(ShoppingListDAO shoppingListDAO, GroupDao groupDao, UserDao userDao) {
        this.shoppingListDAO = shoppingListDAO;
        this.groupDao = groupDao;
        this.userDao = userDao;
        //this.shoppingList = shoppingList;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/group/{groupId}/lists", method = RequestMethod.POST)
    public void createShoppingList(@RequestParam String groupId, @Valid @RequestBody ShoppingListDTO newList, Principal user) {

        shoppingListDAO.create(newList.getListName(), newList.getListDescription(), Long.parseLong(groupId), (long)userDao.findIdByUsername((user.getName())));
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value ="/lists/{listId}", method = RequestMethod.PUT)
    public void updateShopList(@RequestBody ShoppingList shoppingList, @PathVariable Long listId){
        System.out.println(listId);
        shoppingList.setListId(listId);
        shoppingListDAO.updateShoppingList(shoppingList.getListName(), shoppingList.getListDescription(), listId, shoppingList.getListClaimedBy());
    }

    @RequestMapping(value = "/group/{groupId}/lists", method = RequestMethod.GET)
    public List<ShoppingList> findAll(@RequestParam String groupId) {
        return shoppingListDAO.getListsByGroupId(Long.parseLong(groupId));
    }

    @RequestMapping(value = "/lists/{listId}", method = RequestMethod.GET)
    public ShoppingList getSingleList(@RequestParam String listId) {
        return shoppingListDAO.getIndividualList(Long.parseLong(listId));
    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/lists/{listId}", method = RequestMethod.GET)
//    public ShoppingList getUserNameByListId(@RequestParam Long listId){
//        return shoppingListDAO.getUserNameByListId(listId);
//    }




}



