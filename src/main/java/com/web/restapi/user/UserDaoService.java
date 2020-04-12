package com.web.restapi.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static int usersCount=0;

    private static List<User> users=new ArrayList<>(){{
        add(new User(++usersCount,"Abhishek",new Date()));
        add(new User(++usersCount,"Madaan",new Date()));
        add(new User(++usersCount,"Vishu",new Date()));
    }};


    List<User> findAll(){
        return users;
    }

    User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        //Brute force search not optimized
        for(User user:users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
}
