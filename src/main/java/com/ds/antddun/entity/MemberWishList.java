package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
@SequenceGenerator(name="WISH_SEQ_GEN",sequenceName = "WISH_SEQ", initialValue = 1, allocationSize = 1)

public class MemberWishList extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISH_SEQ_GEN")
    private Long wno;

    private String wishList;
    private int price;
    private int rate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
