package com.ds.antddun.repository;

import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.SosoJobBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SosoBoardRepository extends JpaRepository<SosoJobBoard, Long> {
//    @Query(value = "SELECT sjb, m FROM SosoJobBoard sjb, Member m WHERE sjb.member=m.mno and sjb.category=:category")
    @Query(value = "SELECT sjb FROM SosoJobBoard sjb WHERE sjb.category=:category")
    List<SosoBoardDTO> getListByCategory(String category);


}
