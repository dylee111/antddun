package com.ds.antddun.service;

import com.ds.antddun.dto.JayuReplyDTO;
import com.ds.antddun.entity.*;

import java.util.List;

public interface JayuReplyService {

    //댓글 등록
    Long register(JayuReplyDTO jayuReplyDTO);

    //댓글 목록
    List<JayuReply> getReplyByJayuNo(Long jayuNo);

    //댓글 수정
    void modify(JayuReplyDTO jayuReplyDTO);

    //댓글 삭제
    void remove(Long jayuRno);

    default JayuReply dtoToEntity(JayuReplyDTO jayuReplyDTO) {

        JayuReply jayuReply = JayuReply.builder()
                .jayuRno(jayuReplyDTO.getJayuRno())
                .text(jayuReplyDTO.getText())
                .member(Member.builder().mno(jayuReplyDTO.getMno()).build())
                .jayuBoard(JayuBoard.builder().jayuNo(jayuReplyDTO.getJayuNo()).build())
                .build();
        return jayuReply;
    }

    default JayuReplyDTO entityToDTO(JayuReply jayuReply){

        JayuReplyDTO jayuReplyDTO = JayuReplyDTO.builder()
                .jayuRno(jayuReply.getJayuRno())
                .jayuNo(jayuReply.getJayuBoard().getJayuNo())
                .mno(jayuReply.getMember().getMno())
                .text(jayuReply.getText())
                .regDate(jayuReply.getRegDate())
                .modDate(jayuReply.getModDate())
                .build();

        return jayuReplyDTO;
    }

}