package com.ds.antddun.service;

import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.UploadImageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
public class QnaServiceImpl implements QnaService {

    @Autowired
    private UploadImageRepository imageRepository;

    @Autowired
    private QnaBoardRepository qnaBoardRepository;

    @Override
    public Long register(QnaBoardDTO qnaBoardDTO, Member member) {

        QnaBoard qnaBoard = dtoToEntity(qnaBoardDTO);

        qnaBoard.setMember(member);
        qnaBoardRepository.save(qnaBoard);
        return qnaBoard.getQnaNo();

    }

    @Override
    public void modify(){
    }

    @Override
    public void delete() {

    }


    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> getBoardList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("qnaNo").descending());
        Page<QnaBoard> result = qnaBoardRepository.findAll(pageable);
        List<QnaBoardDTO> boardDTOList = new ArrayList<>();

        for (QnaBoard board : result) {
            QnaBoardDTO boardDTO = QnaBoardDTO.builder()
                    .qnaNo(board.getQnaNo())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .category(board.getCategory())
                    .ddun(board.getDdun())
                    .modDate(board.getModDate())
                    .regDate(board.getRegDate())
                    .cnt(board.getCnt())
                    .build();
            boardDTOList.add(boardDTO);
            log.info("serviceImpl result >>"+boardDTOList);
        }
        Function<QnaBoard, QnaBoardDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

}
