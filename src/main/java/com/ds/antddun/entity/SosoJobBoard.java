package com.ds.antddun.entity;

import com.ds.antddun.entity.BaseEntity;
import com.ds.antddun.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
@Data
@SequenceGenerator(name = "SOSOJOB_SEQ_GEN", sequenceName = "SOSOJOB_SEQ", initialValue = 1, allocationSize = 1)
public class SosoJobBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOSOJOB_SEQ_GEN")
    private Long sosoNo;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private int cnt;
    private int ddun;

    private String category;


    @ManyToOne(fetch = FetchType.LAZY) // 영속성 (CasacadeType.ALL 제거 : 영속성을 제거하여 Member 엔티티가 중복으로 저장되는 현상 X)

    private Member member;
}
