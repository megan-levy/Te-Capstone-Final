package com.techelevator.model;

public class ShoppingList {

    private Long listId;
    private String listName;
    private Long groupId;
    private Long itemCount;
    //private String retailName;
    private Boolean listClaimed;
    private String listDescription;

    public ShoppingList() { }



    public ShoppingList(Long listId, String listName, Long groupId, Boolean listClaimed, String listDescription, Long itemCount){
        this.listId = listId;
        this.listName = listName;
        this.groupId = groupId;
        //this.retailName = retailName;
        this.listClaimed = listClaimed;
        this.listDescription = listDescription;
        this.itemCount = itemCount;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
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


//    public String getRetailName() {
//        return retailName;
//    }
//
//    public void setRetailName(String retailName) {
//        this.retailName = retailName;
//    }

    public Boolean getListClaimed() {
        return listClaimed;
    }

    public void setListClaimed(Boolean listClaimed) {
        this.listClaimed = listClaimed;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }
}
