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
@SequenceGenerator(name="JAYUCATE_SEQ_GEN",sequenceName = "JAYUCATE_SEQ", initialValue = 1, allocationSize = 1)
public class JayuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JAYUCATE_SEQ_GEN")
    private int jayuCateNo;

    private String jayuCateName;

}
