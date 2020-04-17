package com.web.restapi.student;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentDAOservice {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Student student){
        entityManager.persist(student);
        return student.getId();
    }

}
