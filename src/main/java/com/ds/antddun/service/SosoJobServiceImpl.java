package com.ds.antddun.service;

import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.SosoBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
public class SosoJobServiceImpl implements SosoJobService {

    @Autowired
    private SosoBoardRepository sosoBoardRepository;

    @Override
    public Long register(SosoBoardDTO sosoBoardDTO) {

        log.info(sosoBoardDTO);

        SosoJobBoard sosoJobBoard = dtoToEntity(sosoBoardDTO);

        log.info(sosoJobBoard);

        sosoBoardRepository.save(sosoJobBoard);

        return sosoJobBoard.getSosoNo();
    }

    @Override
    public void modify() {

    }

    @Override
    public void delete() {

    }

    @Override
    public PageResultDTO<SosoBoardDTO, SosoJobBoard> getList(PageRequestDTO requestDTO, Member member) {
        Pageable pageable = requestDTO.getPageable(Sort.by("sosoNo"));

        Page<SosoJobBoard> result = sosoBoardRepository.findAll(pageable);

        Function<SosoJobBoard, SosoBoardDTO> fn = (entity -> entityToDTO(entity, member));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public List<SosoBoardDTO> getListByCategory(SosoJobBoard sosoJobBoard) {

//        SosoBoardDTO dto = entityToDTO(sosoJobBoard);
//
//        List<SosoBoardDTO> result = sosoBoardRepository.getListByCategory(category);

        return null;
    }
}
