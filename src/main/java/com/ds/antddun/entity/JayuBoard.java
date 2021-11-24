package com.ds.antddun.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member","jayuCategory"})
@Data
@SequenceGenerator(name = "JAYU_SEQ_GEN", sequenceName = "JAYU_SEQ", initialValue = 1, allocationSize = 1)
public class JayuBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JAYU_SEQ_GEN")
    private Long jayuNo;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 99900)
    private String content;


    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // 영속성 (CasacadeType.ALL 제거 : 영속성을 제거하여 Member 엔티티가 중복으로 저장되는 현상 X)
    private JayuCategory jayuCategory;

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }

}
