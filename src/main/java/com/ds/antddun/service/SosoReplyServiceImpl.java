package com.ds.antddun.service;

import com.ds.antddun.dto.SosoReplyDTO;
import com.ds.antddun.entity.SosoReply;
import com.ds.antddun.repository.SosoReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SosoReplyServiceImpl implements SosoReplyService{

    private final SosoReplyRepository sosoReplyRepository;

    @Override
    public Long register(SosoReplyDTO sosoReplyDTO) {

        SosoReply sosoReply = dtoToEntity(sosoReplyDTO);

        sosoReplyRepository.save(sosoReply);

        return sosoReply.getSosoReplyNo();
    }

    @Override
    public List<SosoReply> getListBySosoNo(Long sosoNo) {

        List<SosoReply> result = sosoReplyRepository.getReplyListBySosoNo(sosoNo);

        return result;
    }
}
