package com.revature.models;




/**
 * Base constructs for users, store only the integer representation of roles in the db for easier role checking
 */

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "ers_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "user_role_id")
    private Integer userRole;


    @OneToMany(mappedBy="user")
    private Set<Reimbursement> reimbursements;


//    @OneToOne(mappedBy = "user")
//    private Reimbursement reimbusrsement1;
//
//    @OneToOne(mappedBy = "user2")
//    private Reimbursement reimbusrsement2;
////
//    @OneToOne
//    @JoinColumn(name = "userRole")
//    private Role role;

    public User() {
        super();
    }

    public User(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User(int userId, String username, String password, String firstname, String lastname, String email, Integer userRole) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.userRole = userRole;
    }

    public User( String username, String password, String firstname, String lastname, String email, Integer userRole) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFirstname(), user.getFirstname()) &&
                Objects.equals(getLastname(), user.getLastname()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getUserRole(), user.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword(), getFirstname(), getLastname(), getEmail(), getUserRole());
    }

    @Override
    public String toString() {
        return "userId=" + userId +
                " username= " + username + ' ' +" " +
                " password= " + password + ' ' +" " +
                " firstname= " + firstname + ' ' +" " +
                " lastname= " + lastname + ' ' +" " +
                " email= " + email + ' ' +" " +
                " userRole= " + userRole;
    }
}

