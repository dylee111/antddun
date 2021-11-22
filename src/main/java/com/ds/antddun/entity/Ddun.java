package com.ds.antddun.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
@SequenceGenerator(name = "DDUN_SEQ_GEN", sequenceName = "DDUN_SEQ", initialValue = 1)
public class Ddun {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DDUN_SEQ_GEN")
    private Long DdunId;

    private Long inputAmount;
    private Long outputAmount;

    @CreationTimestamp
    private Timestamp chargeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
