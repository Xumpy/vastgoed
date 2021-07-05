package com.xumpy.vastgoed.scrape.era;

import com.xumpy.vastgoed.interfaces.Vastgoed;

public class EraVastgoed extends Vastgoed {
    @Override
    public boolean equals(Object object) {
        if (object instanceof Vastgoed){
            Vastgoed vastgoed = (Vastgoed) object;
            return this.getUniqueName().equals(vastgoed.getUniqueName());
        }
        return false;
    }
}
