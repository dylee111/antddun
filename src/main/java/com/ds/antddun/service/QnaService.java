package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.*;

import java.util.List;

public interface QnaService {


    PageResultDTO<QnaBoardDTO, Object[]> getList(PageRequestDTO requestDTO);

    Long register(QnaBoardDTO qnaBoardDTO, Member member);

//    QnaBoardDTO getBoard(int qnaNo);

//    List<JobList> getListByJob(String job);
//    List<JobList> getListByJobNo (int jno);

    void modify();

    void delete();


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



    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard, Long likesCnt /*, Long reviewCnt*/) {

        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .title(qnaBoard.getTitle())
                .writer(qnaBoard.getMember().getExperience()+"년차 " + qnaBoard.getMember().getJob().getJob() + " " + qnaBoard.getMember().getLastName() +"개미")
                .content(qnaBoard.getContent())
                .jno(qnaBoard.getJobList().getJno())
                .job(qnaBoard.getJobList().getJob())
                .regDate(qnaBoard.getRegDate())
                .ViewCnt(qnaBoard.getViewCnt())
                .modDate(qnaBoard.getModDate())
                .ddun(qnaBoard.getDdun())
                .build();

        dto.setLikesCnt(likesCnt.intValue());
        System.out.println("entityToDto>>>>"+dto);

        return dto;
    }

}