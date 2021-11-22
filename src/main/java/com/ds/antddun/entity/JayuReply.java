package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "jayuBoard")
public class JayuReply extends BaseEntity{
    @Id
    @SequenceGenerator(name = "JAYUREPLY_SEQ_GEN", sequenceName = "JAYUREPLY_SEQ", initialValue = 1, allocationSize = 1)
    private Long jayuRno;

    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private JayuBoard jayuBoard;
}
