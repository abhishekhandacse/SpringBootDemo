package com.web.restapi.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class StudentRepositoryCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StudentRepositoryCommandLineRunner.class);
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void run(String... args) throws Exception {
        Student student=new Student("Jill","Admin");
        studentRepository.save(student);
        log.info("New User is created : "+student);

        Optional<Student> byId = studentRepository.findById(1L);
        log.info("User is retrived"+byId);

        List<Student> all = studentRepository.findAll();

        log.info("All Users :"+all);

    }
}
