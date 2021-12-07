package com.techelevator.model;

public class List {

    private Long listId;
    private String listName;
    private Long groupId;
    private String groupName;
    private String retailName;
    private Boolean listClaimed;

    public List() { }

    public List(Long listId, String listName, Long groupId, String groupName, String retailName, Boolean listClaimed){
        this.listId = listId;
        this.listName = listName;
        this.groupId = groupId;
        this.retailName = retailName;
        this.listClaimed = listClaimed;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRetailName() {
        return retailName;
    }

    public void setRetailName(String retailName) {
        this.retailName = retailName;
    }

    public Boolean getListClaimed() {
        return listClaimed;
    }

    public void setListClaimed(Boolean listClaimed) {
        this.listClaimed = listClaimed;
    }
}
