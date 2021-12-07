package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.List;

public interface GroupDao {

    //Return a list of all groups in database
    List<Group> findAll();

    Group findGroupByGroupId(Long groupId);

    Group findGroupByName(String name);

    //Return groups based solely on userId
    Group findGroupByUserId(Long userId);

    boolean create(String name, String role);
}
