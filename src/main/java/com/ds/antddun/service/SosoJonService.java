package com.ds.antddun.service;

import com.ds.antddun.dto.SosoBoardDTO;
import com.ds.antddun.dto.UploadImageDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.entity.UploadImage;

import java.util.List;
import java.util.stream.Collectors;

public interface SosoJonService {

    void register();

    void modify();

    void delete();

    default SosoBoardDTO entityToDTO(SosoJobBoard sosoJobBoard, List<UploadImage> uploadImageList, Double avg) {

        SosoBoardDTO sosoBoardDTO = SosoBoardDTO.builder()
                .sosoNo(sosoJobBoard.getSosoNo())
                .title(sosoJobBoard.getTitle())
                .content(sosoJobBoard.getContent())
                .regDate(sosoJobBoard.getRegDate())
                .modDate(sosoJobBoard.getModDate())
                .build();

        List<UploadImageDTO> imageDTOList = uploadImageList.stream().map(uploadImage -> {
            return UploadImageDTO.builder()
                    .imgName(uploadImage.getImgName())
                    .path(uploadImage.getPath())
                    .uuid(uploadImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        sosoBoardDTO.setImageDTOList(imageDTOList);
        sosoBoardDTO.setAvg(avg);

        return sosoBoardDTO;
    }
}
