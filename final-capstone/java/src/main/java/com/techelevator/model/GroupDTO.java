package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GroupDTO {

    @NotEmpty
    private String groupname;
    @NotEmpty
    private String role;
    private String groupDescription;
    private Date joined_on;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Date getJoined_on() {
        return joined_on;
    }

    public void setJoined_on(Date joined_on) {
        this.joined_on = joined_on;
    }
}
