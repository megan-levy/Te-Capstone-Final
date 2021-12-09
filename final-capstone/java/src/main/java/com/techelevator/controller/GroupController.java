package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Group;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public Group getGroup(Principal principal) {
//        int groupId = userDao.findIdByUsername(principal.getName());
//        return groupDao.getGroup(groupId).getName();
//    }
    @GetMapping(path = "/groups")
    public List<Group> findAll(){
      //  List <Group> groups = groupDao.findAll();
        Group group = new Group();
        group.setGroupId(1L);
        group.setGroupDescription("This Is A Group");
        group.setName("Party");
        List <Group> groups = new ArrayList<>();
        groups.add(group);
        return groups;
    }


}
