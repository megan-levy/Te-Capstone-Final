package com.techelevator.model;

import java.util.Date;

public class Item {

    private Long listItemId;
    private String itemName;
    private int itemAmount;
    private String listName;
    private Date dateAdded;
    private Long userId;
    private Boolean favorite;
    private Boolean coupon;

    public Item() {}

    public Item (Long listItemId, String itemName, int itemAmount, String listName, Date dateAdded,
                 Long userId, Boolean favorite, Boolean coupon) {
        this.listItemId = listItemId;
        this.itemName = itemName;
        this.itemAmount = itemAmount;
        this.listName = listName;
        this.dateAdded = dateAdded;
        this.userId = userId;
        this.favorite = favorite;
        this.coupon = coupon;
    }

    public Long getListItemId() {
        return listItemId;
    }

    public void setListItemId(Long listItemId) {
        this.listItemId = listItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getCoupon() {
        return coupon;
    }

    public void setCoupon(Boolean coupon) {
        this.coupon = coupon;
    }
}
