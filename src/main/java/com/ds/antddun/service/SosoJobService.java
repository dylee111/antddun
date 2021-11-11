package com.ds.antddun.service;

import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoJobBoard;

import java.util.List;

public interface SosoJobService {

    Long register(SosoBoardDTO sosoBoardDTO, Member member);

    void modify();

    void delete();

    List<SosoBoardDTO> getListByCategory(String category);

    PageResultDTO<SosoBoardDTO, SosoJobBoard> getList(PageRequestDTO requestDTO);

    default SosoBoardDTO entityToDTO(SosoJobBoard sosoJobBoard) {

        SosoBoardDTO sosoBoardDTO = SosoBoardDTO.builder()
                .sosoNo(sosoJobBoard.getSosoNo())
                .title(sosoJobBoard.getTitle())
                .content(sosoJobBoard.getContent())
                .category(sosoJobBoard.getCategory())
                .regDate(sosoJobBoard.getRegDate())
                .mno(sosoJobBoard.getMember().getMno())
                .firstName(sosoJobBoard.getMember().getFirstName())
                .modDate(sosoJobBoard.getModDate())
                .ddun(sosoJobBoard.getDdun())
                .build();

//        List<UploadImageDTO> imageDTOList = uploadImageList.stream().map(uploadImage -> {
//            return UploadImageDTO.builder()
//                    .imgName(uploadImage.getImgName())
//                    .path(uploadImage.getPath())
//                    .uuid(uploadImage.getUuid())
//                    .build();
//        }).collect(Collectors.toList());

//        sosoBoardDTO.setImageDTOList(imageDTOList);
//        sosoBoardDTO.setAvg(avg);

        return sosoBoardDTO;
    }

    default SosoJobBoard dtoToEntity(SosoBoardDTO sosoBoardDTO) {

        MemberDTO memberDTO = new MemberDTO();

        SosoJobBoard sosoJobBoard = SosoJobBoard.builder()
                .sosoNo(sosoBoardDTO.getSosoNo())
                .category(sosoBoardDTO.getCategory())
                .title(sosoBoardDTO.getTitle())
                .content(sosoBoardDTO.getContent())
                .ddun(sosoBoardDTO.getDdun())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();
        return sosoJobBoard;
    }
}
