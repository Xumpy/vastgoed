package com.xumpy.vastgoed.scrape.notaris;

import com.xumpy.vastgoed.interfaces.Vastgoed;

import java.util.Date;

public class NotarisVastgoed extends Vastgoed{
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

    @Override
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public Date getScraped() {
        return new Date();
    }

    public void setScraped(Date scraped) {
        this.scraped = scraped;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Vastgoed){
            Vastgoed vastgoed = (Vastgoed) object;
            return this.getUniqueName().equals(vastgoed.getUniqueName())
                    && (vastgoed.getProvider() == null || this.getProvider() == null || this.getProvider().equals(vastgoed.getProvider()))
                    && (vastgoed.getLocation() == null || this.getLocation() == null || this.getLocation().equals(vastgoed.getLocation()))
                    && (vastgoed.getType() == null || this.getType() == null || this.getType().equals(vastgoed.getType()))
                    && (vastgoed.getAddress() == null || this.getAddress() == null || this.getAddress().equals(vastgoed.getAddress()))
                    && (vastgoed.getDescription() == null || this.getDescription() == null || this.getDescription().equals((vastgoed.getDescription())))
                    && (vastgoed.getPrice() == null || this.getPrice() == null || this.getPrice().equals(vastgoed.getPrice()))
                    && (vastgoed.getSize() == null || this.getSize() == null || this.getSize().equals(vastgoed.getSize()));
        }
        return false;
    }
}
