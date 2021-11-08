package com.ds.antddun.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "DDUN_SEQ_GEN", sequenceName = "DDUN_SEQ", initialValue = 1)
public class Ddun {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DDUN_SEQ_GEN")
    private Long DdunId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long totalAmount;
    private Long inputAmount;
    private Long outputAmount;

    private LocalDateTime chargeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
