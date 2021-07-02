package com.xumpy.vastgoed.db;

import com.xumpy.vastgoed.interfaces.Vastgoed;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="VASTGOED")
public class VastgoedDBPojo extends Vastgoed {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="PK_ID") Integer pkId;
    @Column(name="UNIQUENAME") private String uniqueName;
    @Column(name="PROVIDER") private String provider;
    @Column(name="TYPE") private String type;
    @Column(name="LOCATION") private String location;
    @Column(name="PRICE") private String price;
    @Column(name="ADDRESS") private String address;
    @Column(name="SIZE") private String size;
    @Column(name="DESCRIPTION") private String description;
    @Column(name="STATE") private String state;
    @Column(name="SCRAPED") private Date scraped;

    @Override
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    @Override
    public Date getScraped() {
        return scraped;
    }

    public void setScraped(Date scraped) {
        this.scraped = scraped;
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
    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
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

    public VastgoedDBPojo(){}

    public VastgoedDBPojo(Vastgoed vastgoed){
        this.provider = vastgoed.getProvider();
        this.address = vastgoed.getAddress();
        this.type = vastgoed.getType();
        this.description = vastgoed.getDescription();
        this.location = vastgoed.getLocation();
        this.price = vastgoed.getPrice();
        this.state = vastgoed.getState();
        this.uniqueName = vastgoed.getUniqueName();
        this.size = vastgoed.getSize();
        this.scraped = vastgoed.getScraped();
    }
}
