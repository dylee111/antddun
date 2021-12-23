package com.ds.antddun.repository;

import com.ds.antddun.entity.QnaLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QnaLikesRepository extends JpaRepository<QnaLikes, Long> {

    //좋아요 여부 체크
    @Query("SELECT ql FROM QnaLikes ql WHERE ql.qnaBoard.qnaNo=:qnaNo AND ql.member.mno=:mno ")
    QnaLikes checkLikeByBnoAndMno(Long qnaNo, Long mno);

    //좋아요 추가
    @Modifying
    @Query(value = "INSERT INTO QNA_LIKES (QNA_NO, MNO) VALUES(:qnaNo, :mno) ", nativeQuery = true)
    void addLikes(Long qnaNo, Long mno);

    //좋아요 삭제
    @Modifying
    @Query(value = "DELETE FROM QNA_LIKES WHERE QNA_NO = :qnaNo AND MNO = :mno ", nativeQuery = true)
    void deleteLikes(Long qnaNo, Long mno);

    //좋아요 개수
    @Query("SELECT COUNT(ql.qnaBoard.qnaNo) FROM QnaLikes ql WHERE ql.qnaBoard.qnaNo = :qnaNo ")
    int countLikes(Long qnaNo);

}
