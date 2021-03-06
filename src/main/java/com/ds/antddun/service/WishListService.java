package com.ds.antddun.service;

import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.MemberWishList;

import java.util.List;

public interface WishListService {
    Long register(MemberWishListDTO memberWishListDTO, Member member);

    void modify(MemberWishListDTO memberWishListDTO, Member member);

    void remove(Long wno);

    List<MemberWishList> getListByMno(Long mno);

    int wishListCnt(Long mno);

    default MemberWishList dtoToEntity(MemberWishListDTO wishListDTO, Member member) {

        MemberWishList wishList = MemberWishList.builder()
                .wno(wishListDTO.getWno())
                .wishList(wishListDTO.getWishList())
                .price(wishListDTO.getPrice())
                .rate(wishListDTO.getRate())
                .member(member)
                .dDay(wishListDTO.getDDay())
                .build();
        return wishList;
    }
}
