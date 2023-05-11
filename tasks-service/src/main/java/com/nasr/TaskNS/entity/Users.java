package com.nasr.TaskNS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "users")
//public class UserEntity implements UserDetails {
public class Users {
    public Users(String username,
                 String password,
                 String email,
                 List<Role> roles,
                 List<Tasks> tasks) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.tasks = tasks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "userPassword")
    private String password;
    // we called it userPassword not password in order to avoid the collision with the
    // method coming from UserDetalis in security

    @Column(name = "email")
    private String email;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "task_id"))
    @JsonIgnore
    private List<Tasks> tasks;

    public void addTaskToUser(Tasks task) {
        tasks.add(task);
    }

    public void removeTaskFromAssignedTasks(Tasks task) {
        tasks.remove(task);
    }
}
