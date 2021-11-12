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
    private String category;
    private Long mno; //username

    private LocalDateTime regDate, modDate;

    public JayuBoardDTO(String title, String content, String category, LocalDateTime regDate, LocalDateTime modDate){
        this.title = title;
        this.content = content;
        this.category = category;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
