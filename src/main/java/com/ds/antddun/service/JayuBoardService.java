package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.Member;

import java.util.List;

public interface JayuBoardService {

    Long register(JayuBoardDTO jayuBoardDTO, Member member);

    JayuBoard findById(Long jayuNo);

    List<JayuBoard> findAll();


    default JayuBoardDTO entityToDTO(JayuBoard jayuBoard) {
        JayuBoardDTO jayuBoardDTO = JayuBoardDTO.builder()
                .jayuNo(jayuBoard.getJayuNo())
                .title(jayuBoard.getTitle())
                .writer(jayuBoard.getMember().getJob().getJob() + " " +jayuBoard.getMember().getExperience()+"년차 " + jayuBoard.getMember().getLastName() +"개미")
                .content(jayuBoard.getContent())
                .regDate(jayuBoard.getRegDate())
                .modDate(jayuBoard.getModDate())
                .build();
        return jayuBoardDTO;
    }
    default JayuBoard dtoToEntity(JayuBoardDTO jayuBoardDTO) {
        MemberDTO memberDTO = new MemberDTO();
        JayuBoard jayuBoard = JayuBoard.builder()
                .jayuNo(jayuBoardDTO.getJayuNo())
                .title(jayuBoardDTO.getTitle())
                .content(jayuBoardDTO.getContent())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();
        return jayuBoard;
    }
}
