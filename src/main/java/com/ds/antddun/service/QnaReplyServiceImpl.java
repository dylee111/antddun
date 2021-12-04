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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService{

    private final QnaReplyRepository qnaReplyRepository;

    @Override
    public Long register(QnaReplyDTO qnaReplyDTO) {
        QnaReply qnaReply = dtoToEntity(qnaReplyDTO);
        qnaReplyRepository.save(qnaReply);
        return qnaReply.getQnaRno();
    }


    @Override
    public List<QnaReply> getListByQnaNo(Long qnaNo) {

        List<QnaReply> result = qnaReplyRepository.getReplyListByQnaNo(qnaNo);

        return result;
    }

    @Transactional
    @Override
    public void replyModify(QnaReplyDTO qnaReplyDTO) {
        Optional<QnaReply> result = qnaReplyRepository.findById(qnaReplyDTO.getQnaRno());

        if (result.isPresent()) {
            QnaReply qnaReply = result.get();
            log.info("wondering"+qnaReply);
            qnaReply.setReplyText(qnaReplyDTO.getReplyText());
            qnaReplyRepository.save(qnaReply);
        }
    }

    @Transactional
    @Override
    public void deleteByQnaRno(Long qnaRno) {
        qnaReplyRepository.deleteById(qnaRno);
    }

    @Transactional
    @Override
    public void deleteByQnaNo(Long qnaNo) {
        qnaReplyRepository.deleteByQnaNo(qnaNo);
    }

}
