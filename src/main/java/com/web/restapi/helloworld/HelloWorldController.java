package com.web.restapi.helloworld;

import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {
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
