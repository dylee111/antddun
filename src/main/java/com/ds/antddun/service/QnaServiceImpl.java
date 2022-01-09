package com.ds.antddun.service;

import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.entity.QnaReply;
import com.ds.antddun.repository.JobListRepository;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.QnaReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private QnaBoardRepository qnaBoardRepository;

    @Autowired
    private QnaReplyRepository qnaReplyRepository;

    @Autowired
    private QnaLikesService qnaLikesService;

    @Autowired
    private  QnaReplyService qnaReplyService;


    @Transactional
    @Override
    public PageResultDTO<QnaBoardDTO, Object[]> getListAll(PageRequestDTO requestDTO){
        Page<Object[]> result = qnaBoardRepository.searchPage(
                requestDTO.getCate(),
                requestDTO.getType(),
                requestDTO.getKeyword(),
                requestDTO.getPageable(Sort.by("regDate").descending())
        );

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
                (QnaBoard) arr[0],
                (Long) arr[1],
                (Long) arr[2]
        ));

        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    @Override
    public Long register(QnaBoardDTO qnaBoardDTO, Member member) {
        QnaBoard qnaBoard = dtoToEntity(qnaBoardDTO);
        qnaBoard.setMember(member);
        qnaBoardRepository.save(qnaBoard);

        return qnaBoard.getQnaNo();
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
        qnaReplyService.deleteByQnaNo(qnaNo);
        qnaLikesService.deleteLikes(qnaNo, mno);

        qnaBoardRepository.deleteById(qnaNo);
    }

    @Transactional
    @Override
    public void setSolvedSelected(Long qnaNo, Long replyNo) {
        Optional<QnaBoard> result = qnaBoardRepository.findById(qnaNo);
        result.get().setSolved(true);

        Optional<QnaReply> result2 = qnaReplyRepository.findById(replyNo);
        result2.get().setSelected(true);
    }

}
