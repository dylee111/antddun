package com.ds.antddun.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class
QnaReplyDTO {
    private Long qnaRno;
    private String replyText;
    private Long mno;
    private Long qnaNo;
    private LocalDateTime regDate, modDate;
}
