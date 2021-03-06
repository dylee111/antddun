package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;

import java.util.List;

public interface SosoJobService {

    Long register(SosoBoardDTO sosoBoardDTO, SosoCategoryDTO sosoCategoryDTO, Member member);

    void modify(SosoBoardDTO sosoBoardDTO);

    void delete(SosoBoardDTO sosoBoardDTO);

    List<SosoJobBoard> getListByCategory(String category);
    List<SosoJobBoard> getListByCategoryNo(int categoryNo);

    SosoBoardDTO read(Long sosoNo);

    List<SosoJobBoard> getListLimit(int categoryNo); // 등록일자 순으로 10개

    int updateCnt(Long sosoNo);

    PageResultDTO<SosoBoardDTO, SosoJobBoard> getList(int category, SosoPageRequestDTO sosoPageRequestDTO);
    PageResultDTO<SosoBoardDTO, SosoJobBoard> getListByKeyword(int category,String keyword, SosoPageRequestDTO sosoPageRequestDTO);

        default SosoBoardDTO entityToDTO(SosoJobBoard sosoJobBoard) {

        SosoBoardDTO sosoBoardDTO = SosoBoardDTO.builder()
                .sosoNo(sosoJobBoard.getSosoNo())
                .title(sosoJobBoard.getTitle())
                .content(sosoJobBoard.getContent())
                .categoryNo(sosoJobBoard.getCategory().getCateNo())
                .categoryName(sosoJobBoard.getCategory().getSosoCateName())
                .experience(sosoJobBoard.getMember().getExperience())
                .mno(sosoJobBoard.getMember().getMno())
                .job(sosoJobBoard.getMember().getJob().getJob())
                .firstName(sosoJobBoard.getMember().getFirstName())
                .lastName(sosoJobBoard.getMember().getLastName())
                .ddun(sosoJobBoard.getDdun())
                .regDate(sosoJobBoard.getRegDate())
                .modDate(sosoJobBoard.getModDate())
                .build();
        return sosoBoardDTO;

    }

    default SosoJobBoard dtoToEntity(SosoBoardDTO sosoBoardDTO) {

        MemberDTO memberDTO = new MemberDTO();
        SosoCategoryDTO sosoCategoryDTO = new SosoCategoryDTO();

        SosoJobBoard sosoJobBoard = SosoJobBoard.builder()
                .sosoNo(sosoBoardDTO.getSosoNo())
                .category(SosoCategory.builder().cateNo(sosoCategoryDTO.getCateNo()).build())
                .title(sosoBoardDTO.getTitle())
                .content(sosoBoardDTO.getContent())
                .ddun(sosoBoardDTO.getDdun())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();

        return sosoJobBoard;
    }
}
