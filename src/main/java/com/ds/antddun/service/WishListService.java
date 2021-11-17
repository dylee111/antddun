package com.ds.antddun.service;

import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.MemberWishList;

public interface WishListService {
    Long register(MemberWishListDTO memberWishListDTO, Member member);

    default MemberWishList dtoToEntity(MemberWishListDTO wishListDTO, Member member) {

        MemberWishList wishList = MemberWishList.builder()
                .wishList(wishListDTO.getWishList())
                .price(wishListDTO.getPrice())
                .rate(wishListDTO.getRate())
                .member(member)
                .build();
        return wishList;
    }
}
