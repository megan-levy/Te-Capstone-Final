package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.Date;
import java.util.List;
//import java.util.Random;

public interface GroupDao {

    //Return a list of all groups in database
    List<Group> findAll();

    String findGroupByGroupId(Long groupId);

    Group findGroupByName(String name);

    //Return groups based solely on userId
    Group findGroupByUserId(Long userId);

    Group getGroup(Long groupId);

    void create(String name, String groupDescription, Long userId);

    //void joinGroup(String inviteCode, Random randomCode);
}
