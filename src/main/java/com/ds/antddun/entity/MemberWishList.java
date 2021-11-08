package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name="WISH_SEQ_GEN",sequenceName = "WISH_SEQ", initialValue = 1, allocationSize = 1)

public class MemberWishList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISH_SEQ_GEN")
    private Long wno;

    private String wishList;

    @ManyToOne
    private Member member;
}
