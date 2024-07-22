package rest.api.task.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
// getters and setters are necessary for Hibernate and some Spring modules to function
@Getter
@Setter
public class AppUser {

    private long id;
    private String username;
    private String gender;
    private Integer age;
    private LocalDateTime accountCreationTimestamp;

    // this constructor is used by unit tests
    public AppUser(String username, String gender, int age) {

        this.username = username;
        this.gender = gender;
        this.age = age;

    }

    // the empty constructor is used by Hibernate and some Spring modules
    public AppUser() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrePersist // Get the date of AppUser creation automatically
    // before the AppUser entity is persisted for the first time.
    protected void onCreate() {
        accountCreationTimestamp = LocalDateTime.now();
    }

}

