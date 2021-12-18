package com.ds.antddun.entity;

import com.ds.antddun.dto.QnaReplyDTO;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
/*
@SequenceGenerator(name="QNAREPLY_SEQ_GEN",sequenceName = "QNAREPLY_SEQ", initialValue = 1, allocationSize = 1)
*/

@Entity
@Builder
@Getter
@Setter
@ToString(exclude = {"member", "qnaBoard"})
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate  //변경된 컬럼만 업데이트
public class QnaReply extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaRno;

    private String replyText;

    private boolean isSelected;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private QnaBoard qnaBoard;
}
