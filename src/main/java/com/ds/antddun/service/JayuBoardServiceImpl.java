package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.JayuBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
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

    @Override
    public JayuBoardDTO read(Long jayuNo) {
        Optional<JayuBoard> result = jayuBoardRepository.findById(jayuNo);
        return result.isPresent()?entityToDTO(result.get()):null;
    }

//    @Override
//    public List<JayuBoard> findAll() {
//        List<JayuBoard> jayuBoards = jayuBoardRepository.findAll();
//        return jayuBoards;
//    }

    //게시물 목록
    @Override
    public PageResultDTO<JayuBoardDTO,JayuBoard> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        Page<JayuBoard> result = jayuBoardRepository.findAll(pageable);

        Function<JayuBoard, JayuBoardDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }


    //카테고리 별 게시물 목록
    @Override
    public PageResultDTO<JayuBoardDTO, JayuBoard> getListByCate(int jayuCateNo, PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("regDate").descending());
        log.info(">>>>>jayuCateNo"+jayuCateNo);
        Page<JayuBoard> result = jayuBoardRepository.getListByCate(jayuCateNo, pageable);
        log.info("getListByCateresult" + result);
        Function<JayuBoard, JayuBoardDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

}