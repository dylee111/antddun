package com.ds.antddun.service;

import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.SosoCategoryDTO;
import com.ds.antddun.dto.SosoPageRequestDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QSosoJobBoard;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.SosoBoardRepository;
import com.ds.antddun.repository.SosoCategoryRepository;
import com.ds.antddun.repository.SosoReplyRepository;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class SosoJobServiceImpl implements SosoJobService {

    private final SosoBoardRepository sosoBoardRepository;
    private final SosoCategoryRepository sosoCategoryRepository;

    @Autowired
    private SosoReplyRepository sosoReplyRepository;

    /* register */
    @Override
    public Long register(SosoBoardDTO sosoBoardDTO,
                         SosoCategoryDTO sosoCategoryDTO, Member member) {

        SosoCategory sosoCategory =
                sosoCategoryRepository.findById(sosoCategoryDTO.getCateNo()).get();
        return sosoBoardRepository.save(SosoJobBoard.builder()
                .sosoNo(sosoBoardDTO.getSosoNo())
                .title(sosoBoardDTO.getTitle())
                .content(sosoBoardDTO.getContent())
                .member(member)
                .ddun(sosoBoardDTO.getDdun())
                .category(sosoCategory)
                .build()).getSosoNo();

    }

    /* 게시글 수정 */
    @Override
    public void modify(SosoBoardDTO sosoBoardDTO) {
        Optional<SosoJobBoard> result = sosoBoardRepository.findById(sosoBoardDTO.getSosoNo());

        if (result.isPresent()) {
            SosoJobBoard sosoJobBoard = result.get();

            sosoJobBoard.setTitle(sosoBoardDTO.getTitle());
            sosoJobBoard.setContent(sosoBoardDTO.getContent());
            sosoJobBoard.setDdun(sosoBoardDTO.getDdun());

            sosoBoardRepository.save(sosoJobBoard);
        }
    }

    /* 게시글 삭제 */
    @Transactional
    @Override
    public void delete(SosoBoardDTO sosoBoardDTO) {

        Optional<SosoJobBoard> sosoJobBoard = sosoBoardRepository.findById(sosoBoardDTO.getSosoNo());

        if (sosoJobBoard.isPresent()) { sosoJobBoard.get().setCategory(null); }

        sosoReplyRepository.deleteBySosoBoardNo(sosoBoardDTO.getSosoNo());
        sosoBoardRepository.deleteById(sosoBoardDTO.getSosoNo());
    }

    /* Paging */
    @Override
    public PageResultDTO<SosoBoardDTO, SosoJobBoard> getList(int category, SosoPageRequestDTO sosoPageRequestDTO) {

        Pageable pageable = sosoPageRequestDTO.getPageable(Sort.by("regDate").descending());

        Page<SosoJobBoard> result = sosoBoardRepository.findAllByCategory(category, pageable);
        Function<SosoJobBoard, SosoBoardDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<SosoBoardDTO, SosoJobBoard> getListByKeyword(int category, String keyword, SosoPageRequestDTO sosoPageRequestDTO) {

        Pageable pageable = sosoPageRequestDTO.getPageable(Sort.by("regDate").descending());

        Page<SosoJobBoard> result = sosoBoardRepository.findByKeyword(category, keyword, pageable);
        Function<SosoJobBoard, SosoBoardDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    /* 게시글 조회수 */
    @Override
    public int updateCnt(Long sosoNo) { return sosoBoardRepository.updateCnt(sosoNo); }

    /* Category 이름(String) */
    @Override
    public List<SosoJobBoard> getListByCategory(String category) {
        List<SosoJobBoard> result = sosoBoardRepository.getListByCategory(category);
        return result;
    }
    /* Category 번호(int) */
    @Override
    public List<SosoJobBoard> getListByCategoryNo(int categoryNo) {
        List<SosoJobBoard> result = sosoBoardRepository.getListByCategoryNo(categoryNo);
        return result;
    }

    /* 등록일 기준 10개 */
    @Override
    public List<SosoJobBoard> getListLimit(int categoryNo) { return sosoBoardRepository.getListByCategoryLimit(categoryNo); }

    /* 게시글 상세 보기 */
    @Override
    public SosoBoardDTO read(Long sosoNo) {
        Optional<SosoJobBoard> result = sosoBoardRepository.readBySosoNo(sosoNo);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

//    public Page<SosoJobBoard> getSearch(int categoryNo, String keyword, Pageable pageable) {
//
//    }
}
