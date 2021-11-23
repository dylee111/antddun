package com.ds.antddun.repository;

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

}
