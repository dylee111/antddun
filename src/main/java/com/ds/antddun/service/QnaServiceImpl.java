package com.ds.antddun.service;

import com.ds.antddun.dto.JobListDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.*;
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

import java.util.ArrayList;
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

    //게시물 목록
    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> getBoardList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaNo").descending());
        Page<QnaBoard> result = qnaBoardRepository.findAll(pageable);

        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

/*    //게시물 조회
    @Override
    public QnaBoardDTO getBoard(int qnaNo) {
        List<Object[]> result = qnaBoardRepository.getBoardByQnaNo(qnaNo);
        QnaBoard qnaBoard = (QnaBoard) result.get(0)[0];
        List<UploadImage> imageList = new ArrayList<>();
        return null;
    }*/


    @Override
    public void modify(){
    }

    @Override
    public void delete() {

    }



}
