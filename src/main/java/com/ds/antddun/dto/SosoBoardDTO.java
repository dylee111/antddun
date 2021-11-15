package com.ds.antddun.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class SosoBoardDTO {

    private Long sosoNo;
    private String title;
    private String content;
    private int category;
    private Long mno; // username
    private String firstName;
    private int ddun;

    private double avg;

    @Builder.Default
    private List<UploadImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;
    private LocalDateTime modDate;



}
