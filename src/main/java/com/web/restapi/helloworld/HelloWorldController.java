package com.web.restapi.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationlized(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
    /*
    * Step 1 GET
    * Step 2 URI
    * Step 3 method - " Hello World"
    *
    */
//    @RequestMapping(method= RequestMethod.GET,path="/hello-world")
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return " Hello World";
    }
    //hello-world-bean
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    //hello-world-bean/path-variable/anyname
    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello Dear %s , How Are You !",name));
    }

}
