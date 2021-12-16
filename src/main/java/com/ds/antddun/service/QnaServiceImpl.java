package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.JobListRepository;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.QnaReplyRepository;
import com.ds.antddun.repository.UploadImageRepository;
import lombok.extern.log4j.Log4j2;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class QnaServiceImpl implements QnaService {

    @Autowired
    private JobListRepository jobListRepository;

    @Autowired
    private UploadImageRepository imageRepository;

    @Autowired
    private QnaBoardRepository qnaBoardRepository;

    @Autowired
    private QnaReplyRepository qnaReplyRepository; //xs

    @Autowired
    private QnaLikesService qnaLikesService;

    @Autowired
    private  QnaReplyService qnaReplyService;

    //게시물 등록
    @Transactional
    @Override
    public Long register(QnaBoardDTO qnaBoardDTO, Member member) {

        QnaBoard qnaBoard = dtoToEntity(qnaBoardDTO);
        qnaBoard.setMember(member);
        qnaBoardRepository.save(qnaBoard);

        return qnaBoard.getQnaNo();

    }


    //게시물 전체 목록
    @Override
    public PageResultDTO<QnaBoardDTO, Object[]> getListAll(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        Page<Object[]> result = qnaBoardRepository.getListPage(pageable);

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
                (QnaBoard) arr[0],
                (Long) arr[1],
                (Long) arr[2]
        ));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<QnaBoardDTO, Object[]> getFiveList(PageRequestDTO requestDTO) {
        requestDTO.setSize(5);
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());

        Page<Object[]> result = qnaBoardRepository.getListPage(pageable);

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
                (QnaBoard) arr[0],
                (Long) arr[1],
                (Long) arr[2]
        ));

        return new PageResultDTO<>(result, fn);
    }


    //카테고리 별 게시물 목록
    @Override
    public PageResultDTO<QnaBoardDTO, Object[]> getListByCate(int jno, PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        Page<Object[]> result = qnaBoardRepository.getListByCate(jno, pageable);

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
                (QnaBoard) arr[0],
                (Long) arr[1],
                (Long) arr[2]
        ));
        log.info("arr>>"+ fn);

        return new PageResultDTO<>(result, fn);
    }



    @Override
    public QnaBoardDTO getBoard(Long qnaNo) {
        List<Object[]> result = qnaBoardRepository.getBoardWithAllByQnaNo(qnaNo);

        QnaBoard qnaBoard = (QnaBoard) result.get(0)[0];
        Long likesCnt = (Long) result.get(0)[1];
        Long replyCnt = (Long) result.get(0)[2];

        return entityToDTO(qnaBoard, likesCnt, replyCnt);
    }

    @Transactional
    @Override
    public void modify(QnaBoardDTO qnaBoardDTO, Member member) {

        JobList jobList = jobListRepository.findById(qnaBoardDTO.getJno()).get();
        Optional<QnaBoard> result = qnaBoardRepository.findById(qnaBoardDTO.getQnaNo());

        if (result.isPresent()) {
            QnaBoard entity = result.get();
            entity.setTitle(qnaBoardDTO.getTitle());
            entity.setContent(qnaBoardDTO.getContent());
            entity.setMember(member);
            entity.setJobList(jobList);
            entity.setDdun(qnaBoardDTO.getDdun());
            qnaBoardRepository.save(entity);
        }
    }

    @Transactional
    @Override
    public void deleteAll(Long qnaNo, Long mno)  {

        qnaReplyService.deleteByQnaNo(qnaNo); //해당 게시물의 댓글 삭제
        qnaLikesService.deleteLikes(qnaNo, mno); //해당 게시물의 좋아요 삭제

        qnaBoardRepository.deleteById(qnaNo);
    }


}
