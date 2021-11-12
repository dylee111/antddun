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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
public class SosoJobServiceImpl implements SosoJobService {

    @Autowired
    private SosoBoardRepository sosoBoardRepository;

    /*
    * method : register
    * */
    @Override
    public Long register(SosoBoardDTO sosoBoardDTO, Member member) {

        SosoJobBoard sosoJobBoard = dtoToEntity(sosoBoardDTO);

        sosoJobBoard.setMember(member); // Controller에서 PrincipalDetails 클래스에서 member 객체를 받아서 인증받은 유저를 매개변수로 받음.
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
    public PageResultDTO<SosoBoardDTO, SosoJobBoard> getList(PageRequestDTO requestDTO) {
//        Pageable pageable = requestDTO.getPageable(Sort.by("sosoNo"));
//
//        Page<SosoJobBoard> result = sosoBoardRepository.findAll(pageable);
//
//        Function<SosoJobBoard, SosoBoardDTO> fn = (entity -> entityToDTO(entity)

        return null;

    }

    /*
    * Category 이름(String)
    * */
    @Override
    public List<SosoJobBoard> getListByCategory(String category) {
        List<SosoJobBoard> result = sosoBoardRepository.getListByCategory(category);
        return result;
    }
    /*
     * Category 번호(int)
     * */
    @Override
    public List<SosoJobBoard> getListByCategoryNo(int categoryNo) {
        List<SosoJobBoard> result = sosoBoardRepository.getListByCategoryNo(categoryNo);
        return result;
    }
}
