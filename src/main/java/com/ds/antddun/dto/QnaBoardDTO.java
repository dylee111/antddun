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
@AllArgsConstructor
public class QnaBoardDTO {

    private Long qnaNo;
    private Long mno; // username
    private String title;
    private String content;
    private int jno;
    private String job;
    private String writer;
    private int ddun;
    private int viewCnt;
    private int likesCnt;
    private int replyCnt;
    private boolean isSolved;

    private LocalDateTime regDate;
    private LocalDateTime modDate;


}
