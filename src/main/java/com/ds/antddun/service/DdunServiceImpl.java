package com.ds.antddun.service;

import com.ds.antddun.dto.DdunDTO;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.entity.Ddun;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.DdunRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class DdunServiceImpl implements DdunService{

    private final DdunRepository ddunRepository;

    /*
    * 뚠 충전
    * */
    @Override
    public void saveDdun(DdunDTO ddunDTO) {

        Ddun ddun = dtoToEntity(ddunDTO);

        ddunRepository.save(ddun);
    }

    /*
    * 잔여 뚠 확인
    * */

    @Override
    public Long totalAmountByMno(Long mno) {

        return ddunRepository.totalAmount(mno);
    }

    /* 구매하기 */
    @Override
    public Long sosoBuy(Member member, Long amount, DdunDTO ddunDTO) {

        Long total = ddunRepository.totalAmount(member.getMno());

        if (total >= amount) {
            ddunDTO.setOutputAmount(amount);
            Ddun ddun = dtoToEntity(ddunDTO);

            return ddunRepository.save(ddun).getDdunId();
        }

        return null;
    }

    /* 판매하기 */
    @Override
    public Long sosoSell(MemberDTO memberDto, Long amount, DdunDTO ddunDTO) {

        ddunDTO.setMember(memberDto.getMno());
        ddunDTO.setInputAmount(amount);
        Ddun ddun = dtoToEntity(ddunDTO);

        return ddunRepository.save(ddun).getDdunId();
    }
}
