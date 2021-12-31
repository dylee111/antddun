package com.ds.antddun.repository;

import com.ds.antddun.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    // 회원 가진 권한을 모두 확인해야 하기 때문에 LOAD(Default -> EAGER)로 정의
    @Query("SELECT m " +
            " FROM Member m " +
            " WHERE m.fromSocial=:social AND m.username=:username ")
    Optional<Member> findByEmail(String username, Boolean social);

    @Query("SELECT count(m) FROM Member m WHERE m.username=:username ")
    int idCheck(String username) throws Exception;

    @Query("SELECT count(m) FROM Member m WHERE m.phoneNum=:phoneNum ")
    int phoneNumCheck(String phoneNum) throws Exception;

    @Query("SELECT count(m) FROM Member m WHERE m.username=:username ")
    int recommendUserCheck(String username);

    @Query("SELECT m, j " +
            " FROM Member m, JobList j " +
            " WHERE m.job=j.jno " +
            " AND m.mno=:mno ")
    Member welcomeMsg(Long mno);

    @Query("select m from Member m where username =:username ")
    Member findByUsername(String username);


    @Query("select m.username from Member m where m.phoneNum=:phoneNum ")
    String findByPhoneNum(String phoneNum);

//        select m.*, j.* from member m left join job_list j on m.job_jno = j.jno where m.mno = 1;
//    @Query("select m, j from Member m left join JobList j on m.job.jno=j.jno where m.mno =:mno ")
//    List<Object[]> getMember(Long mno);
}
