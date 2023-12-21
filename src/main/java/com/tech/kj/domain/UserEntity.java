package com.tech.kj.domain;

import com.tech.kj.domain.types.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String middleName;

    @OneToMany(mappedBy = "user")
    private List<ContactEntity> contacts;
    @OneToMany(mappedBy = "user")
    private List<EmailEntity> emails;

    @Column(unique = true)
    private String userName;

    private String password;

    @Column(unique = true)
    private String thirdPartyAccessToken;

    private UserStatus userStatus;

    private boolean isDeleted = false;


}
