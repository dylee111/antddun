package com.ds.antddun.repository;

import com.ds.antddun.entity.SosoJobBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SosoBoardRepository extends JpaRepository<SosoJobBoard, Long>, QuerydslPredicateExecutor<SosoJobBoard> {

    /*카테고리 리스트(이름)*/
    @Query("SELECT sj, sc " +
            " FROM SosoJobBoard sj, SosoCategory sc " +
            " WHERE sj.category=sc.cateNo " +
            " AND sc.sosoCateName=:category ")
    List<SosoJobBoard> getListByCategory(String category);

    /*카테고리 리스트(ID)*/
    @Query("SELECT sj, sc " +
            " FROM SosoJobBoard sj, SosoCategory sc " +
            " WHERE sj.category=sc.cateNo " +
            " AND sc.cateNo=:categoryNo ")
    List<SosoJobBoard> getListByCategoryNo(int categoryNo);

    /*카테고리 별 페이징*/
    @Query(value = "SELECT * FROM soso_job_board WHERE category_cate_no=:categoryNo ", nativeQuery = true)
    Page<SosoJobBoard> findAllByCategory(int categoryNo, Pageable pageable);

    /*카테고리 별 등록일자 순 10개*/
    @Query(value = "SELECT * FROM soso_job_board WHERE category_cate_no =:categoryNo ORDER BY regdate desc limit 10 ", nativeQuery = true)
    List<SosoJobBoard> getListByCategoryLimit(@Param("categoryNo") int categoryNo);

    /*게시글 상세보기*/
    @Query("SELECT sj, sc, m " +
            " FROM SosoJobBoard sj, SosoCategory sc, Member m " +
            " WHERE sj.category.cateNo=sc.cateNo " +
            " AND sj.member.mno=m.mno " +
            " AND sj.sosoNo=:sosoNo ")
    Optional<SosoJobBoard> readBySosoNo(Long sosoNo);

    /*게시글 조회수*/
    @Modifying
    @Transactional
    @Query("UPDATE SosoJobBoard sj SET sj.cnt = cnt + 1 WHERE sj.sosoNo=:sosoNo ")
    int updateCnt(@Param("sosoNo") Long sosoNo);

    @Query(value = "SELECT * FROM soso_job_board WHERE category_cate_no= :categoryNo AND title LIKE %:keyword% ", nativeQuery = true)
    Page<SosoJobBoard> findByKeyword(int categoryNo, String keyword, Pageable pageable);
}
