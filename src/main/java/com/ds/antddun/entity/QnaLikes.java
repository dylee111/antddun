package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"qnaBoard","member"})
@Table(name="qna_likes")
public class QnaLikes {

    //일련번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;

    //게시물 번호
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "qnaNo")
    private QnaBoard qnaBoard;

    //회원 번호
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mno")
    private Member member;

    public QnaLikes(Long lno, QnaBoard qnaBoard, Member member) {
        this.lno = lno;
        this.qnaBoard = qnaBoard;
        this.member = member;
    }
}


