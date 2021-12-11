package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class ShoppingListDTO {
    @NotEmpty
    private String listName;
    private String listDescription;

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
