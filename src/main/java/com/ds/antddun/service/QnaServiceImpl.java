package com.ds.antddun.service;

import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.JobListRepository;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.UploadImageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    private QnaLikesService qnaLikesService;

    //게시물 등록
    @Transactional
    @Override
    public Long register(QnaBoardDTO qnaBoardDTO, Member member) {

        QnaBoard qnaBoard = dtoToEntity(qnaBoardDTO);
        qnaBoard.setMember(member);
        qnaBoardRepository.save(qnaBoard);

        log.info("register>>>>>>"+qnaBoard);

        return qnaBoard.getQnaNo();

    }


    //게시물 전체 목록
    @Override
    public PageResultDTO<QnaBoardDTO, Object[]> getListAll(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        Page<Object[]> result = qnaBoardRepository.getListPage(pageable);
        log.info("alllist"+result);

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
                (QnaBoard) arr[0],
                (Long) arr[1])
        );
        log.info("wdddh"+ fn);
        return new PageResultDTO<>(result, fn);
    }



    //카테고리 별 게시물 목록
    @Override
    public PageResultDTO<QnaBoardDTO, Object[]> getListByCate(int jno, PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        log.info("pageblae>>>"+pageable);
        log.info("jnddo"+jno);
        Page<Object[]> result = qnaBoardRepository.getListByCate(jno, pageable);

        log.info("pageablefirst"+qnaBoardRepository.getListByCate(jno, pageable.first()));
        log.info("resultdd"+result);

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
                (QnaBoard) arr[0],
                (Long) arr[1]
//                (Long) arr[2]
        ));
        log.info("arr>>"+ fn);

        return new PageResultDTO<>(result, fn);
    }



    @Override
    public QnaBoardDTO getBoard(Long qnaNo) {
        List<Object[]> result = qnaBoardRepository.getBoardWithAllByQnaNo(qnaNo);

        QnaBoard qnaBoard = (QnaBoard) result.get(0)[0];
        Long likesCnt = (Long) result.get(0)[1];

        return entityToDTO(qnaBoard, likesCnt);
    }

    @Transactional
    @Override
    public void modify(QnaBoardDTO qnaBoardDTO, Member member) {
        log.info("jnooo"+qnaBoardDTO.getJno());
        log.info("jobbb"+qnaBoardDTO.getJob());
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
    public void delete(Long qnaNo, Long mno) {
        qnaLikesService.deleteLikes(qnaNo, mno);
        qnaBoardRepository.deleteById(qnaNo);
    }



}
