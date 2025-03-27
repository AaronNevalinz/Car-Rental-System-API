package com.car.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
//    private String phone;
//    private String password;
//    private String confirmPassword;
//    private String drivingLicense_number;
//    private enum role{
//        ADMIN,
//        USER
//    }
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
//    private role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Rental> rentals;

    public User() {}

    public User(Long id, String firstName, String lastName, String email, Date created_at) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.created_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    @PrePersist
    protected void onCreate() {
        created_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
//
//    public String getDrivingLicense_number() {
//        return drivingLicense_number;
//    }
//
//    public void setDrivingLicense_number(String drivingLicense_number) {
//        this.drivingLicense_number = drivingLicense_number;
//    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

//    public role getRole() {
//        return role;
//    }
//
//    public void setRole(role role) {
//        this.role = role;
//    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
