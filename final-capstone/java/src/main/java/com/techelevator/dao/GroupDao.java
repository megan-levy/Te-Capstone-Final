package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.Date;
import java.util.List;

public interface GroupDao {

    //Return a list of all groups in database
    List<Group> findAll();

    String findGroupByGroupId(Long groupId);

    Group findGroupByName(String name);

    //Return groups based solely on userId
    Group findGroupByUserId(Long userId);

    Group getGroup(Long groupId);

    Long create(String name, String groupDescription);
}
