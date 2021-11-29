package com.ds.antddun.service;

import com.ds.antddun.dto.QnaReplyDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.entity.QnaReply;

import java.util.List;

public interface QnaReplyService {
    Long register(QnaReplyDTO qnaReplyDTO);
    List<QnaReply> getListByQnaNo(Long qnaNo);

/*
    void replyModify(String replyText, Long qnaReplyNo);*/

    default QnaReply dtoToEntity(QnaReplyDTO qnaReplyDTO) {

        QnaReply qnaReply = QnaReply.builder()
                .qnaRno(qnaReplyDTO.getQnaRno())
                .replyText(qnaReplyDTO.getReplyText())
                .member(Member.builder().mno(qnaReplyDTO.getMno()).build())
                .qnaBoard(QnaBoard.builder().qnaNo(qnaReplyDTO.getQnaNo()).build())
                .build();
        return qnaReply;
    }
}
