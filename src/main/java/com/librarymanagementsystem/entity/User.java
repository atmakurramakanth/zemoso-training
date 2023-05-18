package com.librarymanagementsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_role")
    private String userRole;

    @Column(name = "password")
    private String password;

    public User(){}

    public User(int id, String userName, String userRole, String password) {
        this.id = id;
        this.userName = userName;
        this.userRole = userRole;
        this.password = password;
    }

    public User(String userName, String userRole, String password) {
        this.userName = userName;
        this.userRole = userRole;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
