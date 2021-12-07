package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Group {
    private Long groupId;
    private String name;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Boolean inviteSent;
    private Date joinedOn;
    // not sure if we need this but have left in since on the user Model not sure if related to security/roles access
    private Set<Authority> authorities = new HashSet<>();

    public Group() {
    }

    public Group(Long groupId, String name, Long userId, Boolean inviteSent, Date joinedOn, String authorities) {
        this.groupId = groupId;
        this.name = name;
        this.userId = userId;
        this.inviteSent = true;
        this.joinedOn = joinedOn;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getInviteSent() {
        return inviteSent;
    }

    public void setInviteSent(Boolean inviteSent) {
        this.inviteSent = inviteSent;
    }

    public Date getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Date joinedOn) {
        this.joinedOn = joinedOn;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    //Setter setAuthorities was copied from User model.. Not sure if appropriate here
    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for (String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
            this.authorities.add(new Authority(authority));
        }
    }
}
