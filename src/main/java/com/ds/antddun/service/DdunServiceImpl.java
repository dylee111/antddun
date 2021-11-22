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
import java.util.List;
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

    /* 잔여 뚠 확인 */
    @Override
    public Long totalAmountByMno(Long mno) {

        return ddunRepository.totalAmount(mno);
    }

    /* 구매하기 */
    @Override
    public Long sosoBuy(Member member, Long amount, DdunDTO ddunDTO) {

        Long mno = member.getMno();

        Long total = ddunRepository.totalAmount(mno);
        log.info("TOTAL2222>>>>" + total);
        log.info("AMOUNT222>>>>" + amount);
        log.info("TOTAL - AMOUNT>>>>" + (total-amount) );

        if (total > amount) {
            ddunDTO.setOutputAmount(amount);
            ddunDTO.setInputAmount(0L);
            ddunDTO.setMember(mno);
            Ddun ddun = dtoToEntity(ddunDTO);

            return ddunRepository.save(ddun).getDdunId();
        } else {
            return null;
        }
    }

    /* 판매하기 */
    @Override
    public Long sosoSell(Long mno, Long amount, DdunDTO ddunDTO) {

        ddunDTO.setMember(mno);
        ddunDTO.setInputAmount(amount);
        ddunDTO.setOutputAmount(0L);
        Ddun ddun = dtoToEntity(ddunDTO);

        return ddunRepository.save(ddun).getDdunId();
    }

    /* 리스트 호출 */
    @Override
    public List<Ddun> getListBymno(Long mno) {
        return ddunRepository.getListByMno(mno);
    }
}
