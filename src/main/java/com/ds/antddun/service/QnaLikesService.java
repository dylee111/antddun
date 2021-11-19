package com.ds.antddun.service;


import com.ds.antddun.entity.QnaLikes;

public interface QnaLikesService {

    void addLikes(Long qnaNo, Long mno);

    void unLikes(Long qnaNo, Long mno);

    QnaLikes checkLikes(Long qnaNo, Long mno);

}
