package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.entity.UploadImage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public interface QnaService {


    PageResultDTO<QnaBoardDTO, QnaBoard> getBoardList(PageRequestDTO requestDTO);

    Long register(QnaBoardDTO qnaBoardDTO, Member member);

    void modify();

    void delete();

    default QnaBoard dtoToEntity(QnaBoardDTO qnaBoardDTO) {

        MemberDTO memberDTO = new MemberDTO();

        QnaBoard entity = QnaBoard.builder()
                .qnaNo(qnaBoardDTO.getQnaNo())
                .category(qnaBoardDTO.getCategory())
                .title(qnaBoardDTO.getTitle())
                .content(qnaBoardDTO.getContent())
                .ddun(qnaBoardDTO.getDdun())
                .cnt(qnaBoardDTO.getCnt())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();

        return entity;
    }

    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard ) {
        Member member = new Member();

        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .title(qnaBoard.getTitle())
                .writer(qnaBoard.getMember().getExperience()+"년차 " + qnaBoard.getMember().getJob().getJob() + " " + qnaBoard.getMember().getLastName() +"개미")
                .content(qnaBoard.getContent())
                .category(qnaBoard.getCategory())
                .regDate(qnaBoard.getRegDate())
                .cnt(qnaBoard.getCnt())
                .modDate(qnaBoard.getModDate())
                .ddun(qnaBoard.getDdun())
                .build();

        return dto;
    }

}