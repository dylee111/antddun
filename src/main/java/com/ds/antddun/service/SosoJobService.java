package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoCategory;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.SosoCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface SosoJobService {

    Long register(SosoBoardDTO sosoBoardDTO, SosoCategoryDTO sosoCategoryDTO, Member member);

    void modify();

    void delete();

    List<SosoJobBoard> getListByCategory(String category);
    List<SosoJobBoard> getListByCategoryNo(int categoryNo);

    SosoBoardDTO read(Long sosoNo);

    SosoPageResultDTO<SosoBoardDTO, SosoJobBoard> getList(int category, SosoPageRequestDTO requestDTO);

    default SosoBoardDTO entityToDTO(SosoJobBoard sosoJobBoard) {

        SosoBoardDTO sosoBoardDTO = SosoBoardDTO.builder()
                .sosoNo(sosoJobBoard.getSosoNo())
                .title(sosoJobBoard.getTitle())
                .content(sosoJobBoard.getContent())
                .category(sosoJobBoard.getCategory().getCateNo())
                .mno(sosoJobBoard.getMember().getMno())
                .ddun(sosoJobBoard.getDdun())
                .regDate(sosoJobBoard.getRegDate())
                .modDate(sosoJobBoard.getModDate())
                .build();
        return sosoBoardDTO;

//        List<UploadImageDTO> imageDTOList = uploadImageList.stream().map(uploadImage -> {
//            return UploadImageDTO.builder()
//                    .imgName(uploadImage.getImgName())
//                    .path(uploadImage.getPath())
//                    .uuid(uploadImage.getUuid())
//                    .build();
//        }).collect(Collectors.toList());

//        sosoBoardDTO.setImageDTOList(imageDTOList);
//        sosoBoardDTO.setAvg(avg);

    }

    default SosoJobBoard dtoToEntity(SosoBoardDTO sosoBoardDTO) {

        MemberDTO memberDTO = new MemberDTO();
        SosoCategoryDTO sosoCategoryDTO = new SosoCategoryDTO();

        SosoJobBoard sosoJobBoard = SosoJobBoard.builder()
                .sosoNo(sosoBoardDTO.getSosoNo())
                .category(SosoCategory.builder().cateNo(sosoCategoryDTO.getCateNo()).build())
                .title(sosoBoardDTO.getTitle())
                .content(sosoBoardDTO.getContent())
                .ddun(sosoBoardDTO.getDdun())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();

        return sosoJobBoard;
    }
}
