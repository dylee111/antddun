package com.ds.antddun.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(name="SOSOCATE_SEQ_GEN",sequenceName = "CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
public class SosoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOSOCATE_SEQ_GEN")
    private int cateNo;

    @Column(name= "sosoCateName")
    private String sosoCateName;
}
