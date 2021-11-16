package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.*;

public interface QnaService {


    PageResultDTO<QnaBoardDTO, QnaBoard> getBoardList(PageRequestDTO requestDTO);

    Long register(QnaBoardDTO qnaBoardDTO, JobListDTO jobListDTO, Member member);

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
                .cnt(qnaBoardDTO.getCnt())
                .build();

        System.out.println("dtoToEntity>>"+qnaBoardDTO);
        return entity;
    }



    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard) {

        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .title(qnaBoard.getTitle())
                .writer(qnaBoard.getMember().getExperience()+"년차 " + qnaBoard.getMember().getJob().getJob() + " " + qnaBoard.getMember().getLastName() +"개미")
                .content(qnaBoard.getContent())
                .jno(qnaBoard.getJobList().getJno())
                .job(qnaBoard.getJobList().getJob()) //here
                .regDate(qnaBoard.getRegDate())
                .cnt(qnaBoard.getCnt())
                .modDate(qnaBoard.getModDate())
                .ddun(qnaBoard.getDdun())
                .build();
        System.out.println("entityToDto>>>>"+dto);
        return dto;
    }

}