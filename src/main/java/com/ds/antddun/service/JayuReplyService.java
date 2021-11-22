package com.ds.antddun.service;

import com.ds.antddun.dto.JayuReplyDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.JayuReply;

import java.util.List;

public interface JayuReplyService {

    //댓글 등록
    Long register(JayuReplyDTO jayuReplyDTO);

    //댓글 목록
    List<JayuReplyDTO> getList(Long jayuNo);

    //댓글 수정
    void modify(JayuReplyDTO jayuReplyDTO);

    //댓글 삭제
    void remove(Long jayuRno);

    default JayuReply dtoToEntity(JayuReplyDTO jayuReplyDTO){
        JayuBoard jayuBoard = JayuBoard.builder().jayuNo(jayuReplyDTO.getJayuNo()).build();

        JayuReply jayuReply = JayuReply.builder()
                        .jayuRno(jayuReplyDTO.getJayuRno())
                        .text(jayuReplyDTO.getText())
                        .replyer(jayuReplyDTO.getReplyer())
                        .jayuBoard(jayuBoard)
                        .build();

        return jayuReply;
    }

    default JayuReplyDTO entityToDTO(JayuReply jayuReply){
        JayuReplyDTO jayuReplyDTO = JayuReplyDTO.builder()
                .jayuNo(jayuReply.getJayuRno())
                .text(jayuReply.getText())
                .replyer(jayuReply.getReplyer())
                .regDate(jayuReply.getRegDate())
                .modDate(jayuReply.getModDate())
                .build();

        return jayuReplyDTO;
    }
}
