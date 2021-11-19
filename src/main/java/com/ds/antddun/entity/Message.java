package com.ds.antddun.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"sendMember", "receiveMember"})
@SequenceGenerator(name="MESSAGE_SEQ_GEN",sequenceName = "MESSAGE_SEQ", initialValue = 1, allocationSize = 1)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MESSAGE_SEQ")
    private Long msgNo;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member sendMember;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiveMember;

    private boolean trade;

    @CreationTimestamp
    private Timestamp sendTime;


}
