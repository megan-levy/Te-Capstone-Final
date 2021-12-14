package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private Long groupId;
    private String name;
    private String groupDescription;
    private Date createdOn;
    private String inviteCode;

    public Group() {
    }

    public Group(Long groupId, String name,
                 String groupDescription, Date createdOn) {
        this.groupId = groupId;
        this.name = name;
        this.groupDescription = groupDescription;
        this.createdOn = createdOn;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }


}
