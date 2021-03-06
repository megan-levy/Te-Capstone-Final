package com.techelevator.model;

public class ShoppingList {

    private Long listId;
    private String listName;
    private Long groupId;
    private Long itemCount;
    //private String retailName;
    private Boolean listClaimed;
    private Integer listClaimedBy;
    private String listDescription;
    private String claimedByName;

    public ShoppingList() { }



    public ShoppingList(Long listId, String listName, Long groupId, Integer listClaimedBy, String listDescription, Long itemCount, String claimedByName, Boolean listClaimed){
        this.listId = listId;
        this.listName = listName;
        this.groupId = groupId;
        //this.retailName = retailName;
        this.listClaimedBy = listClaimedBy;
        this.listDescription = listDescription;
        this.itemCount = itemCount;
        this.claimedByName = claimedByName;
        this.listClaimed = listClaimed;
    }

    public Boolean getListClaimed() {
        return listClaimed;
    }

    public void setListClaimed(Boolean listClaimed) {
        this.listClaimed = listClaimed;
    }

    public void setClaimedByName(String claimedByName) {
        this.claimedByName = claimedByName;
    }

    public String getClaimedByName() {
        return claimedByName;
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

    public Integer getListClaimedBy() {
        return listClaimedBy;
    }

    public void setListClaimedBy(Integer listClaimedBy) {
        this.listClaimedBy = listClaimedBy;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }
}
