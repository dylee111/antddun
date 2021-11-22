package com.ds.antddun.repository;

import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.entity.SosoJobBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

    @Query("SELECT qna, COUNT(likes.qnaBoard.qnaNo) " +
            "FROM QnaBoard qna LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "GROUP BY qna.qnaNo ")
    Page<Object[]> getListPage(Pageable pageable);


/*    @Query(value="SELECT * FROM qna_board qna, job_list jl WHERE qna.job_list_jno = jl.jno AND qna.job_list_jno=:jno ",
           countQuery = "SELECT count(*) FROM qna_board qna, job_list jl WHERE qna.job_list_jno = jl.jno AND qna.job_list_jno=:jno ",
           nativeQuery = true)*/
    @Query("SELECT qna, COUNT(likes.qnaBoard.qnaNo) " +
            "FROM QnaBoard qna LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "WHERE job_list_jno=:jno "+
            "GROUP BY qna.qnaNo " )
    Page<Object[]> getListByCate(int jno, Pageable pageable);


    @Query("SELECT qna, COUNT(likes.qnaBoard.qnaNo) " +
            "FROM QnaBoard qna LEFT OUTER JOIN QnaLikes likes " +
            "ON likes.qnaBoard.qnaNo = qna.qnaNo " +
            "WHERE qna.qnaNo=:qnaNo "+
            "GROUP BY qna.qnaNo " )
    List<Object[]> getBoardWithAllByQnaNo(Long qnaNo);


}
