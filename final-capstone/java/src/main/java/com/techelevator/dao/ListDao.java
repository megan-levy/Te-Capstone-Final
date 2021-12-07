package com.techelevator.dao;

import com.techelevator.model.List;

public interface ListDao {

    List getListByGroupId(Long groupId);

    List findListByUserId(Long userId);

    //Trying to get the lists that have not been claimed. Not sure if this is how.
    boolean findByListClaimed (Boolean listClaimed);

    List findListByGroupName(String groupName);

    boolean create(String listName);
}
