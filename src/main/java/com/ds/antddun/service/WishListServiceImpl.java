package com.ds.antddun.service;

import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.repository.MemberWishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

    private final MemberWishListRepository memberWishListRepository;

    @Override
    public Long register(MemberWishListDTO memberWishListDTO, Member member) {
        MemberWishList memberWishList = dtoToEntity(memberWishListDTO, member);

        return memberWishListRepository.save(memberWishList).getWno();
    }

    @Override
    public List<MemberWishList> getListByMno(Long mno) {
        List<MemberWishList> result = memberWishListRepository.getWishListByMno(mno);
        return result;
    }

    // 위시리스트 회원 당 3개로 제한
    @Override
    public int wishListCnt(Long mno) {
        int result = memberWishListRepository.wishListCnt(mno);

        return result;
    }

    @Override
    public void modify(MemberWishListDTO memberWishListDTO, Member member) {
        MemberWishList memberWishList = dtoToEntity(memberWishListDTO, member);

        memberWishListRepository.save(memberWishList);
    }

    @Override
    public void remove(Long wno) {
        memberWishListRepository.deleteById(wno);
    }
}
