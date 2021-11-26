package com.ds.antddun.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class
SosoReplyDTO {
    private Long sosoReplyNo;
    private String replyText;
    private Long mno;
    private Long sosoNo;
    private LocalDateTime regDate, modDate;

}
