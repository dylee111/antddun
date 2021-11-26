package com.ds.antddun.service;

import com.ds.antddun.dto.JayuReplyDTO;
import com.ds.antddun.entity.JayuReply;
import com.ds.antddun.repository.JayuReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JayuReplyServiceImpl implements JayuReplyService{

    private final JayuReplyRepository jayuReplyRepository;

    @Override
    public List<JayuReply> getReplyByJayuNo(Long jayuNo) {

        List<JayuReply> result = jayuReplyRepository.getReplyListByJayuNo(jayuNo);

        return  result;
    }

    @Override
    public Long register(JayuReplyDTO jayuReplyDTO) {
        JayuReply jayuReply = dtoToEntity(jayuReplyDTO);
        jayuReplyRepository.save(jayuReply);

        return jayuReply.getJayuRno();
    }

    @Override
    public void modify(JayuReplyDTO jayuReplyDTO) {
        JayuReply jayuReply = dtoToEntity(jayuReplyDTO);
        jayuReplyRepository.save(jayuReply);
    }

    @Override
    public void remove(Long jayuRno) {
        jayuReplyRepository.deleteById(jayuRno);
    }
}
