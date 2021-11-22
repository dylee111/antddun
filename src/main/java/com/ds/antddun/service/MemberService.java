package com.ds.antddun.service;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JobListDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.AntMemberRoleSet;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.MemberWishList;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    public List<Member> getList();

    String findByPhoneNum(String phoneNum);

    Long join(MemberDTO memberDTO, JobListDTO jobListDTO);

    //소셜로그인 후 추가 정보 기입
    void socialJoin(@AuthenticationPrincipal PrincipalDetails principalDetails,MemberDTO memberDTO, JobListDTO jobListDTO);

    int idCheck(String username) throws Exception;

    int mobileCheck(String mobile) throws Exception;

    boolean recommendUserCheck(String recommendUser);

    Member welcomeMsg(Long mno);

    void modifyMember(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO) {
        Member member = Member.builder()
                .mno(memberDTO.getMno())
                .username(memberDTO.getUsername())
                .password(memberDTO.getPassword())
                .firstName(memberDTO.getFirstName())
                .lastName(memberDTO.getLastName())
                .phoneNum(memberDTO.getPhoneNum())
                .salary(memberDTO.getSalary())
                .createDate(memberDTO.getCreateDate())
//                .recommendUser(memberDTO.getRecommendUser())
                .build();
        return member;
    }

    default MemberDTO entityToDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder()
                .mno(member.getMno())
                .username(member.getUsername())
                .password(member.getPassword())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .phoneNum(member.getPhoneNum())
                .experience(member.getExperience())
//                .recommendUser(member.getRecommendUser())
                .salary(member.getSalary())
                .build();
        return memberDTO;
    }



}

