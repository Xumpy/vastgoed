package com.xumpy.vastgoed.interfaces;

import java.util.Date;

public abstract class Vastgoed {
    public abstract String getUniqueName();
    public abstract String getProvider();
    public abstract String getType();
    public abstract String getLocation();
    public abstract String getDescription();
    public abstract String getPrice();
    public abstract String getAddress();
    public abstract String getSize();
    public abstract String getState();
    public abstract Date getScraped();

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
