package com.ds.antddun.service;

import com.ds.antddun.dto.DdunDTO;
import com.ds.antddun.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListServiceImplTest {

    @Autowired
    private DdunService ddunService;

    @Autowired
    private WishListService wishListService;

    @Test
    void 카운터() {
        System.out.println(wishListService.wishListCnt(1L));
    }

    @Test
    void 잔여금액확인() {
        System.out.println(ddunService.totalAmountByMno(2L));
    }


}