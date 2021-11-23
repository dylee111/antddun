package com.ds.antddun.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@SequenceGenerator(name="MEMBER_SEQ_GEN",sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
//@DynamicInsert // INSERT 시 Null인 값을 제외
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
    private Long mno;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String phoneNum;

    private boolean fromSocial; 

    @Enumerated(EnumType.STRING)
    private AntMemberRoleSet role;

    @ManyToOne(fetch = FetchType.EAGER)
    private JobList job;
    private int experience;

    private Long salary;
    private String startTime;
    private String endTime;

    @CreationTimestamp
    private Timestamp createDate;

}
