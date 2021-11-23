package com.ds.antddun.repository;


import com.ds.antddun.entity.JayuBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JayuBoardRepository extends JpaRepository<JayuBoard, Long>{

    //    @Query("select j from JayuBoard j where jayu_category = :jayuCateNo ")
    @Query(value = "select * from jayu_board where jayu_category_jayu_cate_no=:jayuCateNo ", nativeQuery = true)
    Page<JayuBoard> getListByCate(int jayuCateNo, Pageable pageable);

}
