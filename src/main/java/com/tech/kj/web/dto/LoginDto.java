package com.tech.kj.web.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {

    private String userName;
    String password;
}
