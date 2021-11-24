package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.JayuCategory;
import com.ds.antddun.entity.Member;
public interface JayuBoardService {

    Long register(JayuBoardDTO jayuBoardDTO, Member member);

    JayuBoardDTO read(Long jayuNo);

    //목록 조회
    PageResultDTO<JayuBoardDTO,JayuBoard>  getList(PageRequestDTO pageRequestDTO);

    //카테고리 목록 조회
    PageResultDTO<JayuBoardDTO, JayuBoard> getListByCate(int jayuCateNo, PageRequestDTO pageRequestDTO);

    default JayuBoardDTO entityToDTO(JayuBoard jayuBoard) {
        JayuBoardDTO jayuBoardDTO = JayuBoardDTO.builder()
                .jayuNo(jayuBoard.getJayuNo())
                .jayuCateNo(jayuBoard.getJayuCategory().getJayuCateNo())
                .jayuCateName(jayuBoard.getJayuCategory().getJayuCateName())
                .title(jayuBoard.getTitle())
                .content(jayuBoard.getContent())
                .writer(jayuBoard.getMember().getJob().getJob() + " " +jayuBoard.getMember().getExperience()+"년차 " + jayuBoard.getMember().getLastName() +"개미")
                .regDate(jayuBoard.getRegDate())
                .modDate(jayuBoard.getModDate())
                .build();
        return jayuBoardDTO;
    }
    default JayuBoard dtoToEntity(JayuBoardDTO jayuBoardDTO) {
        JayuCategory jayuCategory = JayuCategory.builder().jayuCateNo(jayuBoardDTO.getJayuCateNo()).build();

        JayuBoard jayuBoard = JayuBoard.builder()
                .jayuNo(jayuBoardDTO.getJayuNo())
                .jayuCategory(jayuCategory)
                .title(jayuBoardDTO.getTitle())
                .content(jayuBoardDTO.getContent())
                .build();
        return jayuBoard;
    }
}
