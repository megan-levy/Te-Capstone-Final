package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class GroupDTO {

    @NotEmpty
    private String groupname;
    @NotEmpty
    private String role;

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
}
