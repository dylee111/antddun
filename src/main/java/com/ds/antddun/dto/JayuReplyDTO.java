package com.ds.antddun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JayuReplyDTO {

    private Long jayuRno, jayuNo;

    private String text, replyer;

    private LocalDateTime regDate, modDate;
}
