package com.ds.antddun.dto;

import com.ds.antddun.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DdunDTO {

    private Long DdunId;
    private Long inputAmount;
    private Long outputAmount;
    private LocalDateTime chargeDate;
    private Long member;
}
