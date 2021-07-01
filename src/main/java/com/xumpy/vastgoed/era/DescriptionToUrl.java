package com.xumpy.vastgoed.era;

import java.util.Locale;

public class DescriptionToUrl {
    public static String transformDescription(String description){
        return description.replace(".", "")
                .replace(",", "")
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "-")
                .replace("²", "2")
                .toLowerCase(Locale.ROOT);
    }
    public static String transform(String base, String description){
        return transformDescription(description);
    }
}
