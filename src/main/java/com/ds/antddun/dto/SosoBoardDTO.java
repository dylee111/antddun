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
public class SosoBoardDTO {

    private Long sosoNo;
    private String title;
    private String writer;

    private double avg;

    private LocalDateTime regDate;
    private LocalDateTime modDate;



}
