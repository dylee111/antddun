package com.ds.antddun.service;

import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.SosoCategoryDTO;
import com.ds.antddun.dto.SosoPageRequestDTO;
import com.ds.antddun.dto.SosoPageResultDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.SosoBoardRepository;
import com.ds.antddun.repository.SosoCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class SosoJobServiceImpl implements SosoJobService {

    private final SosoBoardRepository sosoBoardRepository;
    private final SosoCategoryRepository sosoCategoryRepository;

    /*
    * method : register
    * */
    @Override
    public Long register(SosoBoardDTO sosoBoardDTO,
                         SosoCategoryDTO sosoCategoryDTO, Member member) {

        log.info("SSNO>>>>" + sosoCategoryDTO.getCateNo());
        SosoCategory sosoCategory =
                sosoCategoryRepository.findById(sosoCategoryDTO.getCateNo()).get();
        log.info("SSCATEGORY"+sosoCategory);
        return sosoBoardRepository.save(SosoJobBoard.builder()
                .sosoNo(sosoBoardDTO.getSosoNo())
                .title(sosoBoardDTO.getTitle())
                .content(sosoBoardDTO.getContent())
                .member(member)
                .ddun(sosoBoardDTO.getDdun())
                .category(sosoCategory)
                .build()).getSosoNo();

    }

    @Override
    public void modify() {

    }

    @Override
    public void delete() {

    }

    /*
    * Paging
    * */
    @Override
    public SosoPageResultDTO<SosoBoardDTO, SosoJobBoard> getList(int category, SosoPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("sosoNo").descending());
//        Page<SosoJobBoard> result = sosoBoardRepository.getPageByCategoryNo(category, pageable);

        Page<SosoJobBoard> result = sosoBoardRepository.findAll(pageable);



        Function<SosoJobBoard, SosoBoardDTO> fn = (entity -> entityToDTO(entity));
        log.info("FNFNFNFN" + fn);
        log.info("result>>>>>" + result.getContent());
        return new SosoPageResultDTO<>(result, fn);
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
