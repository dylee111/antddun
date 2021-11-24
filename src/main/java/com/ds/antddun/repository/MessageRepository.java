package com.ds.antddun.repository;

import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

//    @Query("SELECT msg, m.mno, m.firstName, m.lastName, m.username " +
//            " FROM Message msg, Member m " +
//            " WHERE msg.receiveMember.mno=:receiverMno " +
//            " AND msg.sendMember.mno=:sendMno ")
    @Query(value = "SELECT * FROM message WHERE receive_member_mno=:mno OR send_member_mno=:mno ", nativeQuery = true)
    List<Message> getMsgListByMno(@Param("mno") Long receiverMno);

    @Query("SELECT DISTINCT(msg.sendMember) FROM Message msg WHERE msg.receiveMember.mno=:receiver ")
    List<Member> distinctSender(Long receiver);

    @Query("SELECT msg " +
            " FROM Message msg " +
            " WHERE (msg.sendMember.mno=:firstMember OR msg.receiveMember.mno=:firstMember) " +
            " AND (msg.sendMember.mno=:secondMember OR msg.receiveMember.mno=:secondMember) ")
    List<Message> getList(@Param("firstMember") Long firstMember, @Param("secondMember") Long secondMember);

}
