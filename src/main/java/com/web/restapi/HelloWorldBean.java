package com.web.restapi;

public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage() {
         this.message=message;
    }

    @Override
    public String toString() {
        return String.format("Hello World Bean message -> %s",message);
    }
}
