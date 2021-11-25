package com.ds.antddun.service;

import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.entity.JayuCategory;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QJayuBoard;
import com.ds.antddun.repository.JayuBoardRepository;
import com.ds.antddun.repository.JayuCateRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class JayuBoardServiceImpl implements JayuBoardService{

    private final JayuBoardRepository jayuBoardRepository;

    private final JayuCateRepository jayuCateRepository;

    @Override
    public Long register(JayuBoardDTO jayuBoardDTO, Member member){

        JayuCategory cateName = jayuCateRepository.findById(jayuBoardDTO.getJayuCateNo()).get();

        log.info("getJayuCateNo"+ jayuBoardDTO.getJayuCateNo());
        log.info("dto"+jayuBoardDTO);
        JayuBoard jayuBoard = dtoToEntity(jayuBoardDTO);
        jayuBoard.setMember(member);
        jayuBoard.setJayuCategory(cateName);
        jayuBoardRepository.save(jayuBoard);
        return jayuBoard.getJayuNo();
    }

    @Override
    public JayuBoardDTO read(Long jayuNo) {
        log.info("jayuNo>>>>>"+jayuNo);
        Optional<JayuBoard> result = jayuBoardRepository.findById(jayuNo);

        return result.isPresent()?entityToDTO(result.get()):null;
    }

    //게시물 목록
    @Override
    public PageResultDTO<JayuBoardDTO,JayuBoard> getList(PageRequestDTO pageRequestDTO){
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("regDate").descending());
        //검색조건 처리
        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
        Page<JayuBoard> result = jayuBoardRepository.findAll(booleanBuilder, pageable);

        Function<JayuBoard, JayuBoardDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String keyword = pageRequestDTO.getKeyword();
        log.info("keyword>>>>>"+keyword);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QJayuBoard qJayuBoard = QJayuBoard.jayuBoard;
        //jayuNo > 0 조건만 생성
        BooleanExpression expression = qJayuBoard.jayuNo.gt(0L); // gno>0 조건만 생성
        booleanBuilder.and(expression);

        //검색조건이 없는 경우
        if (keyword == null || keyword.trim().length() == 0) {
            return booleanBuilder;
        }

        //검색조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.or(qJayuBoard.title.contains(keyword));

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
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