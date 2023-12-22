package com.tech.kj.domain;

import com.tech.kj.domain.types.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String middleName;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<ContactEntity> contacts = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<EmailEntity> emails = new HashSet<>();

    @Column(unique = true)
    private String userName;

    private String password;

    @Column(unique = true)
    private String thirdPartyAccessToken;

    private UserStatus userStatus;


    private boolean isDeleted = false;


}
