package com.ds.antddun.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="SOSOCATE_SEQ_GEN",sequenceName = "CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
public class SosoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOSOCATE_SEQ_GEN")
    private int cateNo;

    private String sosoCateName;
}
