package com.technical.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @Length(min = 4, message = "*Your username must have at least 4 characters")
    @NotEmpty(message = "*Please provide an username")
    private String username;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private int active;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyDateTime;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id", referencedColumnName = "insurance_id")
    private Insurance insurance;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}