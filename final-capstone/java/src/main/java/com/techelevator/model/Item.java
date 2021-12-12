package com.techelevator.model;

import java.util.Date;

public class Item {

    private Long listItemId;
    private String itemName;
    private int itemAmount;
    private Long listId;
    private Date dateAdded;
    private Long userId;
    private Boolean favorite;
    private Boolean coupon;

    public Item() {}

    public Item (String itemName, int itemAmount, Long listId, Date dateAdded,
                 Long userId, Boolean favorite, Boolean coupon) {
        this.itemName = itemName;
        this.itemAmount = itemAmount;
        this.listId = listId;
        this.dateAdded = dateAdded;
        this.userId = userId;
        this.favorite = favorite;
        this.coupon = coupon;
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

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
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
