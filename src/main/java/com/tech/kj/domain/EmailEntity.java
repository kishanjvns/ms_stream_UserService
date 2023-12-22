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
public class EmailEntity  extends BaseEntity{
    @Column(unique = true)
    private String email;
    private boolean isPrimary;

    private boolean isVerified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;


}
