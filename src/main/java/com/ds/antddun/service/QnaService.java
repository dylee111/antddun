package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.*;

public interface QnaService {

    //목록 조회
    PageResultDTO<QnaBoardDTO, Object[]> getListByCate(int jno, PageRequestDTO requestDTO);

    //목록 전체 조회
    PageResultDTO<QnaBoardDTO, Object[]> getListAll(PageRequestDTO requestDTO);

    //게시물 등록
    Long register(QnaBoardDTO qnaBoardDTO, Member member);

    //게시물 조회
    QnaBoardDTO getBoard(Long qnaNo);

    //게시물 수정, 삭제
    void modify(QnaBoardDTO qnaBoardDTO);
    void delete(Long qnaNo);


    default QnaBoard dtoToEntity(QnaBoardDTO qnaBoardDTO) {

        QnaBoard entity = QnaBoard.builder()
                .qnaNo(qnaBoardDTO.getQnaNo())
                .jobList(JobList.builder().jno(qnaBoardDTO.getJno()).build()) //jno
                .title(qnaBoardDTO.getTitle())
                .content(qnaBoardDTO.getContent())
                .ddun(qnaBoardDTO.getDdun())
                .viewCnt(qnaBoardDTO.getViewCnt())
                .build();
        System.out.println("dtoToEntity>>"+qnaBoardDTO);

        return entity;
    }


    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard, Long likesCnt) {

        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .title(qnaBoard.getTitle())
                .writer(qnaBoard.getMember().getExperience()+"년차 " + qnaBoard.getMember().getJob().getJob() + " " + qnaBoard.getMember().getLastName() +"개미")
                .content(qnaBoard.getContent())
                .jno(qnaBoard.getJobList().getJno())
                .job(qnaBoard.getJobList().getJob())
                .regDate(qnaBoard.getRegDate())
                .viewCnt(qnaBoard.getViewCnt())
                .modDate(qnaBoard.getModDate())
                .ddun(qnaBoard.getDdun())
                .build();
        dto.setLikesCnt(likesCnt.intValue());
        System.out.println("entityToDto>>>>"+dto);

        return dto;
    }

}