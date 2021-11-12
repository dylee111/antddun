package com.ds.antddun.repository;

import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

    @Query("SELECT qna FROM QnaBoard qna WHERE qna.category=:category")
    List<QnaBoardDTO> getListByCategory(String category);

/*    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) " +
            " from Movie m left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m group by m ")
    Page<Object[]> getListPage(Pageable pageable);*/

}
