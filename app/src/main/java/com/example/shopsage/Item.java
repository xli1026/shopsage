package com.example.shopsage;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private String price;
    private String location;
    private String category;
    private boolean purchased;

    public Item(String name, String price, String location,
                String category) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.category = category;
        this.purchased = false;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }


    public boolean isPurchased() {
        return purchased;
    }

    public void changePurchaseStatus() {
        this.purchased = !purchased;
    }

}
