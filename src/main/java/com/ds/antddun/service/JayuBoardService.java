package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.Member;

import java.util.List;

import static com.ds.antddun.entity.QJayuBoard.jayuBoard;

public interface JayuBoardService {

    Long register(JayuBoardDTO jayuBoardDTO, Member member);
//    List<JayuBoardDTO> getListByCategory(JayuBoard jayuBoard);

    default JayuBoardDTO entityToDTO(JayuBoard jayuBoard, Member member) {
        JayuBoardDTO jayuBoardDTO = JayuBoardDTO.builder()
                .Jno(jayuBoard.getJno())
                .title(jayuBoard.getTitle())
                .content(jayuBoard.getContent())
                .category(jayuBoard.getCategory())
                .regDate(jayuBoard.getRegDate())
                .modDate(jayuBoard.getModDate())
                .mno(member.getMno())
                .build();
        return jayuBoardDTO;
    }
    default JayuBoard dtoToEntity(JayuBoardDTO jayuBoardDTO) {
        MemberDTO memberDTO = new MemberDTO();
        JayuBoard jayuBoard = JayuBoard.builder()
                .Jno(jayuBoardDTO.getJno())
                .title(jayuBoardDTO.getTitle())
                .content(jayuBoardDTO.getContent())
                .category(jayuBoardDTO.getCategory())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();
        return jayuBoard;
    }
}
