package com.ds.antddun.repository;

import com.ds.antddun.entity.QnaLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QnaLikesRepository extends JpaRepository<QnaLikes, Long> {

    //qnaNo와 mno를 인자로 받아서 해당 게시물에 해당 회원이 좋아요를 등록한 적이 있는지 체크
    @Query("SELECT ql FROM QnaLikes ql WHERE ql.qnaBoard.qnaNo=:qnaNo AND ql.member.mno=:mno ")
    QnaLikes checkLikeByBnoAndMno(Long qnaNo, Long mno);

    //좋아요
    @Modifying
    @Query(value = "INSERT INTO QNA_LIKES (QNA_NO, MNO) VALUES(:qnaNo, :mno) ", nativeQuery = true)
    void addLikes(Long qnaNo, Long mno);

    //좋아요 삭제
    @Modifying
    @Query(value = "DELETE FROM QNA_LIKES WHERE QNA_NO = :qnaNo AND MNO = :mno ", nativeQuery = true)
    void unLikes(Long qnaNo, Long mno);

    //좋아요 개수 세기
    @Query("SELECT COUNT(ql.qnaBoard.qnaNo) FROM QnaLikes ql WHERE ql.qnaBoard.qnaNo = :qnaNo ")
    int countLikes(Long qnaNo);

}
