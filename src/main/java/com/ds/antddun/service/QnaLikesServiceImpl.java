package com.ds.antddun.service;

import com.ds.antddun.entity.QnaLikes;
import com.ds.antddun.repository.QnaLikesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
public class QnaLikesServiceImpl implements QnaLikesService {

    @Autowired
    private QnaLikesRepository qnaLikesRepository;

    @Transactional
    @Override
    public void addLikes(Long qnaNo, Long mno) {
        qnaLikesRepository.addLikes(qnaNo, mno);
    }

    @Transactional
    @Override
    public void unLikes(Long qnaNo, Long mno)  {
        qnaLikesRepository.unLikes(qnaNo, mno);
    }


    @Override
    public QnaLikes checkLikes(Long qnaNo, Long mno) {
        return qnaLikesRepository.checkLikeByBnoAndMno(qnaNo, mno);
    }

}
