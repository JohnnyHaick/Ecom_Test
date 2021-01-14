package com.ecomtrading.ecomtest_2;

public class Contacts {
    public Contacts(String id, String name, String date_lice) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_lice() {
        return date_of_lice;
    }

    public void setDate_of_lice(String date_of_lice) {
        this.date_of_lice = date_of_lice;
    }

    private	String	name;
    private	String date_of_lice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}
