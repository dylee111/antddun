package com.ds.antddun.dto;

import com.ds.antddun.entity.JobList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobListDTO {
    private int jno;
    private String job;
//    private Member member;


}
