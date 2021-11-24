package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@SequenceGenerator(name="SOSOREPLY_SEQ_GEN",sequenceName = "SOSOREPLY_SEQ", initialValue = 1, allocationSize = 1)
public class SosoReply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOSOREPLY_SEQ")
    private Long sosoReplyNo;

    private String replyText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private SosoJobBoard sosoJobBoard;
}
