package com.ds.antddun.service;

import com.ds.antddun.dto.SosoReplyDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.entity.SosoReply;

import java.util.List;

public interface SosoReplyService {
    Long register(SosoReplyDTO sosoReplyDTO);

    List<SosoReply> getListBySosoNo(Long sosoNo);

    default SosoReply dtoToEntity(SosoReplyDTO sosoReplyDTO) {

        SosoReply sosoReply = SosoReply.builder()
                .sosoReplyNo(sosoReplyDTO.getSosoReplyNo())
                .replyText(sosoReplyDTO.getReplyText())
                .member(Member.builder().mno(sosoReplyDTO.getMno()).build())
                .sosoJobBoard(SosoJobBoard.builder().sosoNo(sosoReplyDTO.getSosoNo()).build())
                .build();
        return sosoReply;
    }
}
