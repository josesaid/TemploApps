package com.mx.development.sample01;

import org.springframework.stereotype.Component;

@FormatterType("Bar")
@Component
public class BarFormatter implements Formatter {

    public String format() {
        return "Bar";
    }

}
