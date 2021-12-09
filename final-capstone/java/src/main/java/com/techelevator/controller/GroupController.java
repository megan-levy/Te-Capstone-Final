package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Group;
import com.techelevator.model.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

//@RestController
//@PreAuthorize("isAuthenticated")
//public class GroupController {
//
//    private GroupDao groupDao;
//    private UserDao userDao;
//
//    public GroupController(GroupDao groupDao, UserDao userDao) {
//        this.groupDao = groupDao;
//        this.userDao = userDao;
//    }
//
////    @RequestMapping(path = "/", method = RequestMethod.GET)
////    public Group getGroup(Principal principal){
////        int groupId = userDao.findIdByUsername(principal.getName());
////        return groupDao.getGroup(groupId).getName();
////    public List<> findAll(@RequestParam String name){
////        List <Group> groups = groupDao.findAll();
////        return groups;
////    }
//}
