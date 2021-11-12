package com.ds.antddun.service;

import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.UploadImageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class QnaServiceImpl implements QnaService {

    @Autowired
    private UploadImageRepository imageRepository;

    @Autowired
    private QnaBoardRepository qnaBoardRepository;

    @Override
    public Long register(QnaBoardDTO qnaBoardDTO, Member member) {

        QnaBoard qnaBoard = dtoToEntity(qnaBoardDTO);

        qnaBoard.setMember(member);
        qnaBoardRepository.save(qnaBoard);
        return qnaBoard.getQnaNo();

    }

    @Override
    public void modify(){
    }

    @Override
    public void delete() {

    }

    @Override
    public List<QnaBoardDTO> getListByCategory(QnaBoard qnaBoard) {
        return null;
    }

/*    @Override
    public PageResultDTO<QnaBoardDTO, QnaBoard> getList(PageRequestDTO requestDTO, Member member) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = qnaBoardRepository.getListByCategory(pageable);

        Function<Object[], QnaBoardDTO> fn = (arr -> entityToDTO(
//                (Movie) arr[0],
//                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
//                (Double) arr[2],
//                (Long) arr[3])
        );
        return new PageResultDTO<>(result, fn);
    }*/

    @Override
    public List<QnaBoard> getList(Pageable pageable) {
        return qnaBoardRepository.findAll();
    }

    @Override
    public List<QnaBoardDTO> getBoardList(){
        List<QnaBoard> boardList = qnaBoardRepository.findAll();
        List<QnaBoardDTO> boardDTOList = new ArrayList<>();

        for (QnaBoard board : boardList) {
            QnaBoardDTO boardDTO = QnaBoardDTO.builder()
                    .qnaNo(board.getQnaNo())
                    .writer(board.getMember().getLastName() + "개미")
                    .title(board.getTitle())
                    .content(board.getContent())
                    .category(board.getCategory())
                    .ddun(board.getDdun())
                    .job(board.getMember().getJob().getJob())
                    .experience(board.getMember().getExperience())
                    .modDate(board.getModDate())
                    .regDate(board.getRegDate())
                    .cnt(board.getCnt())
                    .build();
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }

}
