package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public interface QnaService {


    PageResultDTO<QnaBoardDTO, QnaBoard> getBoardList(PageRequestDTO requestDTO);

    Long register(QnaBoardDTO qnaBoardDTO, JobListDTO jobListDTO, Member member);

//    List<JobList> getListByJob(String job);
//    List<JobList> getListByJobNo (int jno);

    void modify();

    void delete();


    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard ) {
        Member member = new Member();
        JobListDTO jobListDTO = new JobListDTO();

        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .title(qnaBoard.getTitle())
                .writer(qnaBoard.getMember().getExperience()+"년차 " + qnaBoard.getMember().getJob().getJob() + " " + qnaBoard.getMember().getLastName() +"개미")
                .content(qnaBoard.getContent())
                .jno(qnaBoard.getJno().getJno()) //here
                .job(qnaBoard.getJno().getJob()) //here
                .regDate(qnaBoard.getRegDate())
                .cnt(qnaBoard.getCnt())
                .modDate(qnaBoard.getModDate())
                .ddun(qnaBoard.getDdun())
                .build();

        return dto;
    }


    default QnaBoard dtoToEntity(QnaBoardDTO qnaBoardDTO, JobListDTO jobListDTO) {

        MemberDTO memberDTO = new MemberDTO();

        QnaBoard entity = QnaBoard.builder()
                .qnaNo(qnaBoardDTO.getQnaNo())
                .jno(JobList.builder().jno(jobListDTO.getJno()).build()) //jno
                .title(qnaBoardDTO.getTitle())
                .content(qnaBoardDTO.getContent())
                .ddun(qnaBoardDTO.getDdun())
                .cnt(qnaBoardDTO.getCnt())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();

        return entity;
    }

}