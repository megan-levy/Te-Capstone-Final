package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class ShoppingListDTO {
    @NotEmpty
    private String listName;
    private String listDescription;
    private Boolean isClaimed;

    public Boolean getClaimed() {
        return isClaimed;
    }

    public void setClaimed(Boolean claimed) {
        isClaimed = claimed;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }
}
