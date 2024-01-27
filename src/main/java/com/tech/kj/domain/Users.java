package com.tech.kj.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users extends BaseEntity{
    private String firstName;
    private String lastName;
    private String middleName;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Contacts> contacts = new HashSet<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Emails> emails = new HashSet<>();

    @Column(unique = true)
    private String userName;

    private String password;

    private boolean isDeleted = false;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<Roles> roles;

}
