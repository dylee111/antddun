package com.ds.antddun.dto;

import com.ds.antddun.entity.QnaBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class QnaBoardDTO {

    private Long qnaNo;
    private String title;
    private String content;
    private String category;
    private String author;
    private Long mno; // username
    private int ddun;
    private int cnt;

    private double avg;

    @Builder.Default
    private List<UploadImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public QnaBoard toEntity(){
        QnaBoard build = QnaBoard.builder()
                .qnaNo(qnaNo)
                .author(author)
                .title(title)
                .content(content)
                .cnt(cnt)
                .ddun(ddun)
                .category(category)
                .build();
        return build;
    }

    public QnaBoardDTO(Long qnaNo, String title, String content, String category, String author, Long mno, int ddun, int cnt, double avg, List<UploadImageDTO> imageDTOList, LocalDateTime regDate, LocalDateTime modDate) {
        this.qnaNo = qnaNo;
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.mno = mno;
        this.ddun = ddun;
        this.cnt = cnt;
        this.avg = avg;
        this.imageDTOList = imageDTOList;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
