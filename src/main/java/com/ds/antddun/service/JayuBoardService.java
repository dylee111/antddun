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

    default JayuBoardDTO entityToDTO(JayuBoard jayuBoard) {
        JayuBoardDTO jayuBoardDTO = JayuBoardDTO.builder()
                .jayuNo(jayuBoard.getJayuNo())
                .title(jayuBoard.getTitle())
                .content(jayuBoard.getContent())
                .category(jayuBoard.getCategory())
                .regDate(jayuBoard.getRegDate())
                .modDate(jayuBoard.getModDate())
                .mno(jayuBoard.getMember().getMno())
                .build();
        return jayuBoardDTO;
    }
    default JayuBoard dtoToEntity(JayuBoardDTO jayuBoardDTO) {
        MemberDTO memberDTO = new MemberDTO();
        JayuBoard jayuBoard = JayuBoard.builder()
                .jayuNo(jayuBoardDTO.getJayuNo())
                .title(jayuBoardDTO.getTitle())
                .content(jayuBoardDTO.getContent())
                .category(jayuBoardDTO.getCategory())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();
        return jayuBoard;
    }
}
