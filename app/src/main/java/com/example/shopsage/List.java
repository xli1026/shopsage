package com.example.shopsage;

import java.io.Serializable;
import java.util.ArrayList;

public class List implements Serializable {
    private String name;
    public ArrayList<Item> items;

    public List(String name) {
        this.name = name;
        this.items = new ArrayList<Item>();
    }
    public String getName() {
        return name;
    }

    public static ArrayList<List> createLists(int numLists) {
        ArrayList<List> contacts = new ArrayList<List>();

        for (int i = 1; i <= numLists; i++) {
            contacts.add(new List("List " + i));
        }

        return contacts;
    }
}
