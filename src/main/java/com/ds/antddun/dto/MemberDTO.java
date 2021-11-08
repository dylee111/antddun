package com.ds.antddun.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long mno;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private boolean fromSocial;
    private String job;
    private int experience;
//    private boolean recommendUser;
    private Long salary;
    private String startTime;
    private String endTime;
    private String role;
    private Timestamp createDate;
}
