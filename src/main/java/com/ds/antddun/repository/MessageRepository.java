package com.ds.antddun.repository;

import com.ds.antddun.entity.GroupBySender;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // 1:1 메시지 리스트
    @Query(value = "SELECT * FROM message WHERE receive_member_mno=:mno OR send_member_mno=:mno ", nativeQuery = true)
    List<Message> getMsgListByMno(@Param("mno") Long receiverMno);

    // 작성자 중복 제거
//    @Query("SELECT DISTINCT(msg.sendMember), msg.board, msg.trade FROM Message msg WHERE msg.receiveMember.mno=:receiver ")
    @Query(value = "SELECT DISTINCT(msg.send_member_mno) as sendMno, msg.board_soso_no as board, msg.trade as trade, m.first_name as firstName, m.last_name as lastName " +
            " FROM message msg LEFT OUTER JOIN member m " +
            " WHERE msg.receive_member_mno=:receiver " +
            " AND msg.receive_member_mno != msg.send_member_mno " +
            " AND msg.send_member_mno = m.mno "
    , nativeQuery = true)
    List<GroupBySender> distinctSender(Long receiver);

    @Query("SELECT msg.sendMember, msg.board, msg.trade " +
            " FROM Message msg " +
            " WHERE msg.receiveMember.mno=:receiveMember " +
            " AND msg.receiveMember != msg.sendMember " +
            " GROUP BY msg.sendMember, msg.board ")
    List<GroupBySender> groupBySendMember(@Param("receiveMember")Long receiveMember);

    @Query("SELECT msg " +
            " FROM Message msg " +
            " WHERE (msg.sendMember.mno=:firstMember OR msg.receiveMember.mno=:firstMember) " +
            " AND (msg.sendMember.mno=:secondMember OR msg.receiveMember.mno=:secondMember) ")
    List<Message> getList(@Param("firstMember") Long firstMember, @Param("secondMember") Long secondMember);

    // 안읽은 메시지 개수 카운트
    @Query("SELECT COUNT(msg.msgNo) FROM Message msg WHERE msg.msgRead = 0 ")
    int unreadMsg();

    // 읽은 메시지로 변환
    @Modifying
    @Query("UPDATE Message msg SET msg.msgRead = 1 WHERE msg.msgNo=:msgNo ")
    void readMsgChange(@Param("msgNo") Long msgNo);

    // 거래중 / 거래완료
    @Modifying
    @Query("UPDATE Message msg SET msg.trade=:tradeState WHERE msg.board=:sosoNo AND msg.sendMember=:sendMember ")
    void changeTradeState(@Param("tradeState") boolean tradeState,
                          @Param("sosoNo") Long sosoNo,
                          @Param("sendMember") Long sendMember);

}


