package com.ds.antddun.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberWishListDTO {
    private Long wno;
    private String wishList;
    private int price;
    private int rate;
    private Long member;
}
