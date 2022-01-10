package com.ds.antddun.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"member","jobList"})
@Data
@SequenceGenerator(name = "QNA_SEQ_GEN", sequenceName = "QNA_SEQ", initialValue = 1, allocationSize = 1)
public class QnaBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QNA_SEQ_GEN")
    private Long qnaNo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 99900)
    private String content;

    @ColumnDefault("0")
    private int viewCnt;
    private int ddun;

    private boolean isSolved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobList_jno")
    private JobList jobList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
