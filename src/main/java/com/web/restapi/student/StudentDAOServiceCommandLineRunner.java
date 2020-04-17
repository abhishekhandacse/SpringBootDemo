package com.web.restapi.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class StudentDAOServiceCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StudentDAOServiceCommandLineRunner.class);
    @Autowired
    private StudentDAOservice studentDAOservice;
    @Override
    public void run(String... args) throws Exception {
        Student student=new Student("Jeetu","Admin");
        long insert= studentDAOservice.insert(student);
        log.info("New User is created : "+student);

    }
}
