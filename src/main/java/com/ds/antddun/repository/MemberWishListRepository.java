package com.ds.antddun.repository;

import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.MemberWishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberWishListRepository extends JpaRepository<MemberWishList, Long> {
    @Query("SELECT wl,m FROM MemberWishList wl, Member m WHERE wl.member=m.mno AND m.mno=:mno ")
    List<MemberWishList> getWishListByMno(Long mno);

    @Query("SELECT count(wl) FROM MemberWishList wl WHERE wl.member.mno=:mno ")
    int wishListCnt(Long mno);
}
