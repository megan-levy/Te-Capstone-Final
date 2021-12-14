package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Group;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.GroupAlreadyExistsException;
import com.techelevator.model.GroupDTO;
import com.techelevator.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class GroupController {

    private GroupDao groupDao;
    private UserDao userDao;

    public GroupController(GroupDao groupDao, UserDao userDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public void createGroup(@Valid @RequestBody GroupDTO newGroup, Principal user) {
        try {
            Group group = groupDao.findGroupByName(newGroup.getGroupname());
            throw new GroupAlreadyExistsException();
        } catch (Exception e) {
            System.out.println(user);
            Long userId = (long) userDao.findIdByUsername(user.getName());
            groupDao.create(newGroup.getGroupname(), newGroup.getGroupDescription(), userId);
        }
    }

    @RequestMapping(path = "/groups/{groupId}", method = RequestMethod.GET)
    public Group getGroup(@RequestParam String groupId, Principal principal) {
//        int groupId = userDao.findIdByUsername(p
//        rincipal.getName());
//        return groupDao.getGroup(groupId).getName();

//        Long userId = (long) userDao.findIdByUsername(principal.getName());
        if (groupId.isEmpty()) {
            return new Group();
        }

            return groupDao.getGroup(Long.parseLong(groupId));
    }

    @GetMapping(path = "/groups")
    public List<Group> findAll() {
        //  List <Group> groups = groupDao.findAll();
        return groupDao.findAll();
//        group.setGroupId(1L);
//        group.setGroupDescription("This Is A Group");
//        group.setName("Party");
//        List <Group> groups = new ArrayList<>();
//        groups.add(group);
//        return groups;
    }

}
