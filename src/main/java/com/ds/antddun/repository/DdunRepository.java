package com.ds.antddun.repository;

import com.ds.antddun.entity.Ddun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DdunRepository extends JpaRepository<Ddun, Long> {

    @Query(value = "SELECT sum(input_amount)-sum(output_amount) " +
            " FROM ddun " +
            " WHERE member_mno=:mno ",
            nativeQuery = true)
    Long totalAmount(Long mno);

    @Query("SELECT d FROM Ddun d WHERE d.member.mno=:mno ")
    List<Ddun> getListByMno(Long mno);
}
