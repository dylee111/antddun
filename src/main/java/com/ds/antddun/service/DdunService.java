package com.ds.antddun.service;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.config.auth.PrincipalDetailsService;
import com.ds.antddun.dto.DdunDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.Ddun;
import com.ds.antddun.entity.Member;

import java.util.List;

public interface DdunService {

    void saveDdun(DdunDTO ddunDTO);

    Long totalAmountByMno(Long mno);

    List<Ddun> getListBymno(Long mno);

    Long sosoBuy(Member member, Long amount, DdunDTO ddunDTO);
    Long sosoSell(Long mno, Long amount, DdunDTO ddunDTO);

    default Ddun dtoToEntity(DdunDTO ddunDTO) {
        Ddun ddun = Ddun.builder()
                .DdunId(ddunDTO.getDdunId())
                .inputAmount(ddunDTO.getInputAmount())
                .outputAmount(ddunDTO.getOutputAmount())
                .content(ddunDTO.getContent())
                .member(Member.builder().mno(ddunDTO.getMember()).build())
                .build();
        return ddun;
    }

}
