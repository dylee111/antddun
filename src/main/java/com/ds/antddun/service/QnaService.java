package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface QnaService {

    //목록 카테고리별 조회
    PageResultDTO<QnaBoardDTO, Object[]> getListByCate(int jno, PageRequestDTO requestDTO);

    //목록 전체 조회
    PageResultDTO<QnaBoardDTO, Object[]> getListAll(PageRequestDTO requestDTO);

    //목록 최신 순으로 5개까지만
    PageResultDTO<QnaBoardDTO, Object[]> getFiveList(PageRequestDTO requestDTO);

    //게시물 등록
    Long register(QnaBoardDTO qnaBoardDTO, Member member);

    //게시물 조회
    QnaBoardDTO getBoard(Long qnaNo);

    //게시물 수정, 삭제
    void modify(QnaBoardDTO qnaBoardDTO, Member mno);
    void deleteAll(Long qnaNo, Long mno);


    default QnaBoard dtoToEntity(QnaBoardDTO qnaBoardDTO) {

        QnaBoard entity = QnaBoard.builder()
                .qnaNo(qnaBoardDTO.getQnaNo())
                .jobList(JobList.builder().jno(qnaBoardDTO.getJno()).build()) //jno
                .title(qnaBoardDTO.getTitle())
                .content(qnaBoardDTO.getContent())
                .ddun(qnaBoardDTO.getDdun())
                .isSolved(false)
                .viewCnt(qnaBoardDTO.getViewCnt())
                .build();
        System.out.println("dtoToEntity>>"+qnaBoardDTO);

        return entity;
    }


    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard, Long likesCnt, Long replyCnt) {

        QnaBoardDTO dto = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .mno(qnaBoard.getMember().getMno())
                .title(qnaBoard.getTitle())
                .writer(qnaBoard.getMember().getExperience()+"년차 " + qnaBoard.getMember().getJob().getJob() + " " + qnaBoard.getMember().getLastName() +"개미")
                .content(qnaBoard.getContent())
                .jno(qnaBoard.getJobList().getJno())
                .job(qnaBoard.getJobList().getJob())
                .regDate(qnaBoard.getRegDate())
                .viewCnt(qnaBoard.getViewCnt())
                .modDate(qnaBoard.getModDate())
                .isSolved(qnaBoard.isSolved())
                .ddun(qnaBoard.getDdun())
                .build();
        dto.setLikesCnt(likesCnt.intValue());
        dto.setReplyCnt(replyCnt.intValue());
        System.out.println("entityToDto>>>>"+dto);

        return dto;
    }

}