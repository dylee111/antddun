package com.ds.antddun.service;

import com.ds.antddun.dto.QnaReplyDTO;
import com.ds.antddun.dto.SosoReplyDTO;
import com.ds.antddun.entity.QnaReply;
import com.ds.antddun.entity.SosoReply;
import com.ds.antddun.repository.QnaReplyRepository;
import com.ds.antddun.repository.SosoReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService{

    private final QnaReplyRepository qnaReplyRepository;

    @Override
    public Long register(QnaReplyDTO qnaReplyDTO) {

        QnaReply qnaReply = dtoToEntity(qnaReplyDTO);
        log.info("dho"+ qnaReplyDTO.getQnaRno());

        qnaReplyRepository.save(qnaReply);
        return qnaReply.getQnaRno();
    }

    @Override
    public List<QnaReply> getListByQnaNo(Long qnaNo) {

        List<QnaReply> result = qnaReplyRepository.getReplyListByQnaNo(qnaNo);

        return result;
    }

/*    @Override
    @Transactional
    public void replyModify(String replyText, Long sosoReplyNo) {

        qnaReplyRepository.replyModify(replyText, sosoReplyNo);
    }*/




}
