package com.web.restapi.user;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2,message = "Name should have atleast 2 characters")
    private String name;

  //  @JsonIgnore// This annotation helps in static filtering
    @Past
    private Date birthdate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    protected User(){}

    public User(Integer id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
