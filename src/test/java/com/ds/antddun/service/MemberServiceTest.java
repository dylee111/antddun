package com.ds.antddun.service;

import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.JobListRepository;
import com.ds.antddun.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    JobListRepository jobListRepository;

    @Autowired
    private PasswordEncoder encoder;

//    @Test
//    void join() {
//
//        Member member = Member.builder()
//                .email("test@test.com")
//                .password(encoder.encode("1111"))
//                .firstName("이")
//                .lastName("동")
//                .phoneNum("01011112222")
//                .salary("2222222222")
//                .fromSocial(false)
//                .build();
//        memberRepository.save(member);
//    }

    @Test
    void jobList() {

        IntStream.rangeClosed(1,10).forEach(i->{
            JobList jobList = JobList.builder()
                    .job("직무" + i)
                    .build();
            jobListRepository.save(jobList);
        });
    }

    @Test
    void readMember() {

        Member result = memberRepository.welcomeMsg(1L);
        System.out.println(result);

    }

//    @Test
//    public void testRead(){
//        Optional<Member> result = memberRepository.findByEmail("dylee111@kakao.com", false);
//        Member member = result.get();
//        System.out.println(member);
//    }

//    @Test
//    public void findAllTest() {
//
//        System.out.println(jobListRepository.findAll());
//    }

}