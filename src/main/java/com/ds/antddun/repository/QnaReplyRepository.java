package com.ds.antddun.repository;

import com.ds.antddun.entity.QnaReply;
import com.ds.antddun.entity.SosoReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long> {

   @Query("SELECT reply FROM QnaReply reply WHERE reply.qnaBoard.qnaNo=:qnaNo ")
    List<QnaReply> getReplyListByQnaNo(@Param("qnaNo") Long qnaNo);
/*
    @Modifying(clearAutomatically = true)
    @Query("UPDATE QnaReply reply SET reply.replyText = ?1 WHERE reply.sosoReplyNo= ?2")
    void replyModify(String replyText, Long qnaReplyNo);*/
}
