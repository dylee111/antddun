package com.ds.antddun.repository;

import com.ds.antddun.entity.SosoReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SosoReplyRepository extends JpaRepository<SosoReply, Long> {

    @Query("SELECT reply FROM SosoReply reply WHERE reply.sosoJobBoard.sosoNo=:sosoNo ")
    List<SosoReply> getReplyListBySosoNo(@Param("sosoNo") Long sosoNo);

    @Modifying
    @Query("DELETE FROM SosoReply reply WHERE reply.sosoJobBoard.sosoNo=:sosoBoardNo ")
    void deleteBySosoBoardNo(@Param("sosoBoardNo")Long sosoBoardNo);

}
