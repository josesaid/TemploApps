package com.mx.development.sample01;

import org.springframework.stereotype.Component;

@FormatterType("Foo")
@Component
public class FooFormatter implements Formatter {

    public String format() {
        return "foo";
    }

}
