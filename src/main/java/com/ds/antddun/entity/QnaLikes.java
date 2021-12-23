package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"qnaBoard","member"})
@Table(name="qna_likes")
public class QnaLikes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "qnaNo")
    private QnaBoard qnaBoard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mno")
    private Member member;

}


