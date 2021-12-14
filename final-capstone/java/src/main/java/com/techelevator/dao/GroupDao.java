package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.RandomCode;

import java.util.Date;
import java.util.List;
//import java.util.Random;

public interface GroupDao {

    //Return a list of all groups in database
    List<Group> findAll();

    Group findGroupByGroupId(Long groupId);

    Group findGroupByName(String name);

    Group findGroupByCode(String code);

    List<Group> findGroupsByUserId(Long userId);

    Group getGroup(Long groupId);

    void create(String name, String groupDescription, Long userId);

    void joinGroup(String inviteCode, Long userId);

    //void joinGroup(String inviteCode, Random randomCode);
}
