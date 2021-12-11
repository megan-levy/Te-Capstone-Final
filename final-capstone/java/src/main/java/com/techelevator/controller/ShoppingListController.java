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
        //  List <Group> groups = groupDao.findAll();
        return shoppingListDAO.getListsByGroupId(Long.parseLong(groupId));
//        group.setGroupId(1L);
//        group.setGroupDescription("This Is A Group");
//        group.setName("Party");
//        List <Group> groups = new ArrayList<>();
//        groups.add(group);
//        return groups;
    }
}



