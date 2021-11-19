package com.ds.antddun.repository;

import com.ds.antddun.entity.SosoJobBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SosoBoardRepository extends JpaRepository<SosoJobBoard, Long> {

    @Query("SELECT sj, sc " +
            " FROM SosoJobBoard sj, SosoCategory sc " +
            " WHERE sj.category=sc.cateNo " +
            " AND sc.sosoCateName=:category ")
    List<SosoJobBoard> getListByCategory(String category);

    @Query("SELECT sj, sc " +
            " FROM SosoJobBoard sj, SosoCategory sc " +
            " WHERE sj.category=sc.cateNo " +
            " AND sc.cateNo=:categoryNo ")
    List<SosoJobBoard> getListByCategoryNo(int categoryNo);

    @Query(value = "SELECT * FROM soso_job_board sj, soso_category sc WHERE sj.category_cate_no=sc.cateNo AND sc.cateNo=?1 ",
    countQuery = "SELECT COUNT(*) FROM soso_job_board WHERE category_cate_no=?1 ",
    nativeQuery = true)
    Page<SosoJobBoard> getPageByCategoryNo(int categoryNo, Pageable pageable);

}
