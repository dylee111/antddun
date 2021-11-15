package com.ds.antddun.service;

import com.ds.antddun.dto.*;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.entity.UploadImage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public interface QnaService {

    List<QnaBoardDTO> getBoardList();

    List<QnaBoard> getList(Pageable pageable);

    Long register(QnaBoardDTO qnaBoardDTO, Member member);

    void modify();

    void delete();

    List<QnaBoardDTO> getListByCategory(QnaBoard qnaBoard);

/*    PageResultDTO<QnaBoardDTO, QnaBoard> getList(PageRequestDTO requestDTO);*/

    default QnaBoardDTO entityToDTO(QnaBoard qnaBoard , Member member, List<UploadImage> uploadImageList, Long cnt) {

        QnaBoardDTO qnaBoardDTO = QnaBoardDTO.builder()
                .qnaNo(qnaBoard.getQnaNo())
                .title(qnaBoard.getTitle())
                .content(qnaBoard.getContent())
                .category(qnaBoard.getCategory())
                .regDate(qnaBoard.getRegDate())
                .mno(member.getMno())
                .modDate(qnaBoard.getModDate())
                .ddun(qnaBoard.getDdun())
                .build();

        List<UploadImageDTO> imageDTOList = uploadImageList.stream().map(uploadImage -> {
            return UploadImageDTO.builder()
//                    .imgName(uploadImage.getImgName())
//                    .path(uploadImage.getPath())
//                    .uuid(uploadImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        qnaBoardDTO.setImageDTOList(imageDTOList);
        qnaBoardDTO.setCnt(cnt.intValue());

        return qnaBoardDTO;
    }

    default QnaBoard dtoToEntity(QnaBoardDTO qnaBoardDTO) {

        MemberDTO memberDTO = new MemberDTO();

        QnaBoard qnaBoard = QnaBoard.builder()
                .qnaNo(qnaBoardDTO.getQnaNo())
                .category(qnaBoardDTO.getCategory())
                .title(qnaBoardDTO.getTitle())
                .content(qnaBoardDTO.getContent())
                .ddun(qnaBoardDTO.getDdun())
                .cnt(qnaBoardDTO.getCnt())
                .member(Member.builder().mno(memberDTO.getMno()).build())
                .build();

        return qnaBoard;
    }



}