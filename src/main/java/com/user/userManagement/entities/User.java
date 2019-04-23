package com.user.userManagement.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * A user is represented in this beans
 * A user has one role
 * Created by dinanjanag on 3/20/19.
 */
@Entity
@Table(name = "USER")
public class User implements Cloneable {

    @Id
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String contactNo;

    private String emailAddress;

    private String companyName;

    @OneToOne
    @JoinColumn(name = "role")
    private Role role;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean active;

    public User() {

    }

    public User(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.contactNo = user.getContactNo();
        this.emailAddress = user.getEmailAddress();
        this.companyName = user.getCompanyName();
        this.active = user.isActive();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
