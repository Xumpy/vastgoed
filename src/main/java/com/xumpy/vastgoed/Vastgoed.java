package com.xumpy.vastgoed;

import org.jsoup.nodes.Element;

public abstract class Vastgoed {
    public abstract String getUniqueName();
    public abstract String getDescription();
    public abstract String getPrice();
    public abstract String getAddress();
    public abstract String getSize();

    public abstract Vastgoed build(String baseUrl, Element webContent);
}
