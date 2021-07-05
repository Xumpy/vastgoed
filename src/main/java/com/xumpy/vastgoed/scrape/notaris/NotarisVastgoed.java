package com.xumpy.vastgoed.scrape.notaris;

import com.xumpy.vastgoed.interfaces.Vastgoed;

import java.util.Date;

public class NotarisVastgoed extends Vastgoed{
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
