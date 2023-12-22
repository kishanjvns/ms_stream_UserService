package com.tech.kj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.hibernate.usertype.UserType;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    private String createdBy;
    private String updatedBy;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
}
