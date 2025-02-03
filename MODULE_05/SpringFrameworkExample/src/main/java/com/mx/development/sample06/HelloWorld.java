package com.mx.development.sample06;

import lombok.Data;

@Data
public class HelloWorld {
    private String message;

    public void init(){
        System.out.println("Bean is going through init.");
    }
    public void destroy() {
        System.out.println("Bean will destroy now.");
    }
}
