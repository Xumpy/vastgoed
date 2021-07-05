package com.xumpy.vastgoed.interfaces;

import java.util.Date;

public abstract class Vastgoed {
    private String provider;
    private String uniqueName;
    private String type;
    private String location;
    private String price;
    private String address;
    private String size;
    private String description;
    private String state;
    private Date scraped;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Date getScraped() {
        return new Date();
    }

    public void setScraped(Date scraped) {
        this.scraped = scraped;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return this.getUniqueName() + "\n"
                + this.getProvider() + "\n"
                + this.getType() + "\n"
                + this.getLocation() + "\n"
                + this.getDescription() + "\n"
                + this.getPrice() + "\n"
                + this.getAddress() + "\n"
                + this.getSize() + "\n"
                + this.getState() + "\n"
                + this.getScraped() + "\n"
                + "---------------------------";
    }
}
