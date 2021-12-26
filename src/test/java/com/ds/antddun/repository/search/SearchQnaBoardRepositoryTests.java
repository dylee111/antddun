package com.ds.antddun.repository.search;

import com.ds.antddun.repository.QnaBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class SearchQnaBoardRepositoryTests {

    @Autowired
    private QnaBoardRepository qnaBoardRepository;

    @Test
    void search1() {
        qnaBoardRepository.search1();
    }
    @Test
    void searchPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("qnaNo").descending());
        Page<Object[]> result = qnaBoardRepository.searchPage("w","개미",pageable);
    }
}