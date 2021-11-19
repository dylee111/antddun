package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.Member;
import lombok.extern.log4j.Log4j2;

import java.util.List;
public interface JayuBoardService {

    Long register(JayuBoardDTO jayuBoardDTO, Member member);

    JayuBoardDTO read(Long jayuNo);

//    List<JayuBoard> findAll();


    default JayuBoardDTO entityToDTO(JayuBoard jayuBoard) {
        JayuBoardDTO jayuBoardDTO = JayuBoardDTO.builder()
                .jayuNo(jayuBoard.getJayuNo())
                .title(jayuBoard.getTitle())
                .content(jayuBoard.getContent())
                .category(jayuBoard.getCategory())
                .writer(jayuBoard.getMember().getJob().getJob() + " " +jayuBoard.getMember().getExperience()+"년차 " + jayuBoard.getMember().getLastName() +"개미")
                .regDate(jayuBoard.getRegDate())
                .modDate(jayuBoard.getModDate())
                .build();
        return jayuBoardDTO;
    }
    default JayuBoard dtoToEntity(JayuBoardDTO jayuBoardDTO) {
        JayuBoard jayuBoard = JayuBoard.builder()
                .jayuNo(jayuBoardDTO.getJayuNo())
                .title(jayuBoardDTO.getTitle())
                .content(jayuBoardDTO.getContent())
                .category(jayuBoardDTO.getCategory())
                .build();
        return jayuBoard;
    }

    PageResultDTO<JayuBoardDTO,JayuBoard>  getList(PageRequestDTO requestDTO);
}
