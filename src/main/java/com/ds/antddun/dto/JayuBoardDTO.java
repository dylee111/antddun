package com.ds.antddun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JayuBoardDTO {

    private Long jayuNo;
    private String title;
    private String content;
    private String writer;

    private int cnt;
    private int likes;

    private LocalDateTime regDate, modDate;
}
