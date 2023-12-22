package com.tech.kj.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ContactEntity extends BaseEntity{
    @Column(unique = true)
    private String contact;
    private boolean isPrimary;

    private boolean isVerified;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "fkContact")
    private UserEntity user;
}
