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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JobListRepository jobListRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Long join(MemberDTO memberDTO, JobListDTO jobListDTO) {

        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        log.info("GetSTART>>>>>>>>>>>>>>>>"+memberDTO.getStartTime());
        log.info("GetEND>>>>>>>>>>>>>>>>"+memberDTO.getEndTime());
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
    public Date convertDate(String startTime, String endTime) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();

        return null;
    }

    @Override
    public List<Member> welcomeMsg(Long mno) {

//        Member member = Member.builder().mno(mno).build();
        List<Member> result = memberRepository.welcomeMsg(mno);

        return result;
    }
}
