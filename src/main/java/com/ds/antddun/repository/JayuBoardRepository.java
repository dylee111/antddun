package com.ds.antddun.repository;


import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.entity.JayuBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JayuBoardRepository extends JpaRepository<JayuBoard, Long>{
    @Query("SELECT jayu FROM JayuBoard jayu WHERE jayu.category=:category")
    List<JayuBoardDTO> getListByCategory(String category);
}
