package com.ds.antddun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SosoBoardDTO {

    private Long sosoNo;
    private String title;
    private String content;
    private String category;

    private int ddun;

    private double avg;

    @Builder.Default
    private List<UploadImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public SosoBoardDTO(String title, String content, String category, int ddun, double avg,
                        List<UploadImageDTO> imageDTOList, LocalDateTime regDate, LocalDateTime modDate) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.ddun = ddun;
        this.avg = avg;
        this.imageDTOList = imageDTOList;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
