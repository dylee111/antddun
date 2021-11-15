package com.ds.antddun.dto;

import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    private int jno;
    private String job;
    private String writer;
    private int ddun;
    private int cnt;

    @Builder.Default
    private List<UploadImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public QnaBoardDTO(Long qnaNo, String title, String content, int jno, String job, String writer, int ddun, int cnt, List<UploadImageDTO> imageDTOList, LocalDateTime regDate, LocalDateTime modDate) {
        this.qnaNo = qnaNo;
        this.title = title;
        this.content = content;
        this.jno = jno;
        this.job = job;
        this.writer = writer;
        this.ddun = ddun;
        this.cnt = cnt;
        this.imageDTOList = imageDTOList;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
