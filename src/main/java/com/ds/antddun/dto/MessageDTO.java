package com.ds.antddun.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class MessageDTO {
    private Long msgNo;
    private String title;
    private String content;
    private Long sendMno;
    private Long receiveMno;
    private Long board;
    private int msgRead;
    private boolean trade;
    private Timestamp createDate;
}
