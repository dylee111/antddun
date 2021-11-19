package com.ds.antddun.repository;

import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

/*    @Query("SELECT qna FROM QnaBoard qna WHERE qna.category=:category")
    List<QnaBoardDTO> getListByCategory(String category);*/


/*
    @Query("select qna, m, qna.cnt FROM QnaBoard qna inner join Member m ")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select qna from QnaBoard qna where qnaNo=:qnaNo")
    List<Object[]> getBoardByQnaNo(int qnaNo);
*/


}
