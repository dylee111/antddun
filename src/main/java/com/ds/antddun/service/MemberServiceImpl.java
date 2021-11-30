package com.ds.antddun.service;

import com.ds.antddun.dto.JobListDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.AntMemberRoleSet;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.JobListRepository;
import com.ds.antddun.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JobListRepository jobListRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Member> getList() {
        return null;
    }


    @Override
    public String findByPhoneNum(String phoneNum) {
        String username = memberRepository.findByPhoneNum(phoneNum);
        return username;
    }

    /* 회원가입 */
    @Override
    public Long join(MemberDTO memberDTO, JobListDTO jobListDTO) {

        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        JobList jobList = jobListRepository.findById(jobListDTO.getJno()).get();

        return memberRepository.save(Member.builder()
                .mno(memberDTO.getMno())
                .username(memberDTO.getUsername())
                .password(memberDTO.getPassword())
                .firstName(memberDTO.getFirstName())
                .lastName(memberDTO.getLastName())
                .phoneNum(memberDTO.getPhoneNum())
                .salary(memberDTO.getSalary())
                .startTime(memberDTO.getStartTime())
                .endTime(memberDTO.getEndTime())
                .role(AntMemberRoleSet.USER)
                .job(jobList)
                .experience(memberDTO.getExperience())
                .fromSocial(false)
                .createDate(memberDTO.getCreateDate())
                .build()).getMno();

    }

    /* 회원정보 수정 */
    @Transactional
    @Override
    public void modifyMember(MemberDTO memberDTO) {
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        log.info("S.MODIFY.M >>> " + memberDTO);
        JobList jobList = jobListRepository.findById(Integer.parseInt(memberDTO.getJob())).get();
        log.info("S.MODIFY.J >>> " + jobList);

        memberRepository.save(Member.builder()
                .mno(memberDTO.getMno())
                .password(memberDTO.getPassword())
                .username(memberDTO.getUsername())
                .phoneNum(memberDTO.getPhoneNum())
                .firstName(memberDTO.getFirstName())
                .lastName(memberDTO.getLastName())
                .job(jobList)
                .experience(memberDTO.getExperience())
                .salary(memberDTO.getSalary())
                .role(AntMemberRoleSet.USER)
                .startTime(memberDTO.getStartTime())
                .endTime(memberDTO.getEndTime())
                .fromSocial(false)
                .createDate(memberDTO.getCreateDate())
                .build());
    }

    /*
     * 아이디 중복 체크
     * */
    @Override
    public int idCheck(String username) throws Exception {

        int result = 0;

        result = memberRepository.idCheck(username);

        return result;
    }

    /*
     * 연락처 중복 체크
     * */
    @Override
    public int mobileCheck(String phoneNum) throws Exception {

        int result = 0;

        result = memberRepository.phoneNumCheck(phoneNum);

        return result;
    }

    /*
     * 추천인 확인
     * */
    @Override
    public boolean recommendUserCheck(String recommendUser) {

        boolean result = false;
        int userCnt = memberRepository.recommendUserCheck(recommendUser);

        if (userCnt >= 1) {
            result = true;
            return result;
        } else if (userCnt == 0) {
            result = false;
            return result;
        }
        return false;
    }

    /*
    * 시간 변환
    * */

    @Override
    public Member welcomeMsg(Long mno) {
//        Member member = Member.builder().mno(mno).build();
        Member result = memberRepository.welcomeMsg(mno);
//        List<Member> result = memberRepository.welcomeMsg(mno);
        return result;
    }

    @Transactional
    @Override
    public void socialJoin( MemberDTO memberDTO, JobListDTO jobListDTO) {

        JobList jobList = jobListRepository.getById(jobListDTO.getJno());

        Member member = socialDtoToEntity(memberDTO);
        member.setMno(memberDTO.getMno()); //update되도록 mno알려주기
        member.setJob(jobList); //Member에 setter 추가해줌
        memberRepository.save(member);
    }


}
