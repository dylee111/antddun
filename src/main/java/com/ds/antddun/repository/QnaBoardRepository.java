package com.ds.antddun.repository;

import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

    @Query("SELECT qna, m FROM QnaBoard inner join Member m qna WHERE qna.m=:m.mno qna.category=:category")
    List<QnaBoardDTO> getListByCategory(String category);

}