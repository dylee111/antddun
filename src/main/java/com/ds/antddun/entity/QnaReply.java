package com.ds.antddun.entity;

import com.ds.antddun.dto.QnaReplyDTO;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString(exclude = {"member", "qnaBoard"})
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate  //변경된 컬럼만 업데이트
@SequenceGenerator(name="QNAREPLY_SEQ_GEN",sequenceName = "QNAREPLY_SEQ", initialValue = 1, allocationSize = 1)
public class QnaReply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QNAREPLY_SEQ")
    private Long qnaRno;

    private String replyText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private QnaBoard qnaBoard;
}
