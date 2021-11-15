package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.JayuBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class JayuBoardServiceImpl implements JayuBoardService{

    @Autowired
    private JayuBoardRepository jayuBoardRepository;

    @Override
    public Long register(JayuBoardDTO jayuBoardDTO){
        JayuBoard jayuBoard = dtoToEntity(jayuBoardDTO);
        jayuBoardRepository.save(jayuBoard);
        return jayuBoard.getJayuNo();
    }

    @Override
    public JayuBoard findById(Long jayuNo) {
        return jayuBoardRepository.findById(jayuNo).get();
    }

    @Override
    public List<JayuBoard> findAll() {
        List<JayuBoard> jayuBoards = jayuBoardRepository.findAll();
        return jayuBoards;
    }
}
