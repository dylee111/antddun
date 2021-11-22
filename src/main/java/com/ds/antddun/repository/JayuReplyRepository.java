package com.ds.antddun.repository;

import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.JayuReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JayuReplyRepository extends JpaRepository<JayuReply, Long> {
    //JayuBoard 삭제시 댓글 삭제
    @Modifying
    @Query("delete from JayuReply r where r.jayuBoard.jayuNo =:jayuNo")
    void deleteByJayuNo(Long jayuNo);

    //댓글 목록 출력
    List<JayuReply> getRepliesByJayuBoardOrderByJayuRno(JayuBoard jayuBoard);
}
