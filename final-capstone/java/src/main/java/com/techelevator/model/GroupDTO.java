package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GroupDTO {

    @NotEmpty
    private String groupname;
    private String groupDescription;
    private Date created_on;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date joined_on) {
        this.created_on = joined_on;
    }
}
