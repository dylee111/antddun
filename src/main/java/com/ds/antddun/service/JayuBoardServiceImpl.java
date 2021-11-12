package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.JayuBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class JayuBoardServiceImpl implements JayuBoardService{

    @Autowired
    private JayuBoardRepository jayuBoardRepository;

    @Override
    public Long register(JayuBoardDTO jayuBoardDTO, Member member){
        JayuBoard jayuBoard = dtoToEntity(jayuBoardDTO);
        jayuBoard.setMember(member);
        jayuBoardRepository.save(jayuBoard);
        return jayuBoard.getJayuNo();
    }
}
