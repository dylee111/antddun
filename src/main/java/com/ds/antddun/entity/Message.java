package com.ds.antddun.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicUpdate
@SequenceGenerator(name="MESSAGE_SEQ_GEN",sequenceName = "MESSAGE_SEQ", initialValue = 1, allocationSize = 1)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MESSAGE_SEQ")
    private Long msgNo;

    @Column(nullable = false)
    private String msgTitle;
    @Column(nullable = false)
    private String msgContent;

    // fetchType.Lazy -> JSON 오류 발생(InvalidDefinitionException (엔티티 정보를 가져올 때 시점의 문제가 발생)
    @ManyToOne(fetch = FetchType.EAGER)
    private Member sendMember;
    @ManyToOne(fetch = FetchType.EAGER)
    private Member receiveMember;

    @ManyToOne(fetch = FetchType.EAGER)
    private SosoJobBoard board;

    private boolean trade;
    private int msgRead;

    @CreationTimestamp
    private Timestamp sendTime;


}
