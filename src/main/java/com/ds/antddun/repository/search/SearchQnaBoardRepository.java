package com.ds.antddun.repository.search;

import com.ds.antddun.entity.QnaBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchQnaBoardRepository {

    QnaBoard search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
