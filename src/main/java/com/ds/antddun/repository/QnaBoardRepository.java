package com.ds.antddun.repository;

import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.search.SearchQnaBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long>,
        SearchQnaBoardRepository {

    @Query("SELECT qna, COUNT(distinct likes.lno), COUNT(distinct reply.qnaRno) " +
            "FROM QnaBoard qna " +
            "LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "LEFT OUTER JOIN QnaReply reply " +
            "ON reply.qnaBoard.qnaNo = qna.qnaNo " +
            "group by qna.qnaNo " )
    Page<Object[]> getListPage(Pageable pageable);

    @Query("SELECT qna, COUNT(distinct likes.lno), COUNT(distinct reply.qnaRno) " +
            "FROM QnaBoard qna " +
            "LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "LEFT OUTER JOIN QnaReply reply " +
            "ON reply.qnaBoard.qnaNo = qna.qnaNo " +
            "group by qna.qnaNo " )
    Page<Object[]> getFive(Pageable pageable);


    @Query("SELECT qna, COUNT(distinct likes.lno), COUNT(distinct reply.qnaRno) " +
            "FROM QnaBoard qna " +
            "LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "LEFT OUTER JOIN QnaReply reply " +
            "ON reply.qnaBoard.qnaNo = qna.qnaNo " +
            "WHERE job_list_jno=:jno "+
            "group by qna.qnaNo " )
    Page<Object[]> getListByCate(int jno, Pageable pageable);


    @Query("SELECT qna, COUNT(distinct likes.lno), COUNT(distinct reply.qnaRno) " +
            "FROM QnaBoard qna " +
            "LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "LEFT OUTER JOIN QnaReply reply " +
            "ON  reply.qnaBoard.qnaNo = qna.qnaNo " +
            "WHERE qna.qnaNo=:qnaNo "+
            "group by qna.qnaNo " )
    List<Object[]> getBoardWithAllByQnaNo(Long qnaNo);

    @Transactional
    @Modifying
    @Query("update QnaBoard set viewCnt = viewCnt + 1 where qnaNo=:qnaNo")
    void updateViewCnt(Long qnaNo);

}
