package com.ds.antddun.entity;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
@Data
@SequenceGenerator(name = "QNA_SEQ_GEN", sequenceName = "QNA_SEQ", initialValue = 1, allocationSize = 1)
public class QnaBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QNA_SEQ_GEN")
    private Long qnaNo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private int cnt;
    private int ddun;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobList_jno")
    private JobList jno;

    @ManyToOne(fetch = FetchType.EAGER) // 영속성 (CasacadeType.ALL 제거 : 영속성을 제거하여 Member 엔티티가 중복으로 저장되는 현상 X)
    private Member member;

}
