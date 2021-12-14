package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;

import java.util.ArrayList;
import java.util.List;

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
    public Group getGroup(@RequestParam String groupId) {
        if (groupId.isEmpty()) return new Group();
        else return groupDao.getGroup(Long.parseLong(groupId));
    }

    @GetMapping(path = "/groups")
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @GetMapping(path = "/groups/user")
    public List<Group> findAllByUser(Principal user) {
        System.out.println(user);
        return groupDao.findGroupsByUserId((long) userDao.findIdByUsername(user.getName()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/members", method = RequestMethod.POST)
    public void joinGroup(@RequestBody InviteCode invite, Principal user) {
        //Need to have something so you can't join the same group more than once.
//        System.out.println(invite.getCode());

        groupDao.joinGroup(invite.getCode(), (long) userDao.findIdByUsername(user.getName()));
    }



}

