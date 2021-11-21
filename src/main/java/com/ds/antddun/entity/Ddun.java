package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
@Getter
@SequenceGenerator(name = "DDUN_SEQ_GEN", sequenceName = "DDUN_SEQ", initialValue = 1)
public class Ddun {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DDUN_SEQ_GEN")
    private Long ddunId;

    private Long totalAmount;
    private Long inputAmount;
    private Long outputAmount;

    private LocalDateTime chargeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
