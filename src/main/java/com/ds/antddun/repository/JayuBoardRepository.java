package com.ds.antddun.repository;


import com.ds.antddun.entity.JayuBoard;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface JayuBoardRepository extends JpaRepository<JayuBoard, Long>,
        QuerydslPredicateExecutor<JayuBoard> {

    //카테고리 목록 출력
    @Query(value = "select * from jayu_board where jayu_category_jayu_cate_no=:jayuCateNo ", nativeQuery = true)
    Page<JayuBoard> getListByCate(int jayuCateNo, Pageable pageable);

    //조회수
    @Transactional
    @Modifying
    @Query("update JayuBoard set viewCnt = viewCnt + 1 where jayuNo=:jayuNo")
    void updateViewCnt(Long jayuNo);

}
