package com.techelevator.model;

import java.util.Objects;

public class MemberOf {
    private Long userId;
    private Long groupId;
    private boolean inviteAccepted;

    public MemberOf(){}

    public MemberOf(Long userId, Long groupId, boolean inviteAccepted){
        this.userId = userId;
        this.groupId = groupId;
        this.inviteAccepted = false;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public boolean isInviteAccepted() {
        return inviteAccepted;
    }

    public void setInviteAccepted(boolean inviteAccepted) {
        this.inviteAccepted = inviteAccepted;
    }

}
