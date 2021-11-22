package com.ds.antddun.service;

import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
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
        ));
        log.info("arr>>"+ fn);

        return new PageResultDTO<>(result, fn);
    }



    @Override
    public QnaBoardDTO getBoard(Long qnaNo) {
        log.info("qnaNONONO"+ qnaNo);
        List<Object[]> result = qnaBoardRepository.getBoardWithAllByQnaNo(qnaNo);
        log.info("resultqnaNONONO"+ qnaNo);
        log.info("resultresult"+result);

        QnaBoard qnaBoard = (QnaBoard) result.get(0)[0];
        Long likesCnt = (Long) result.get(0)[1];

        return entityToDTO(qnaBoard, likesCnt);
    }


    @Override
    public void modify(){
    }

    @Override
    public void delete() {

    }



}
