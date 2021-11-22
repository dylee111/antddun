package com.ds.antddun.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
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
    @Column(nullable = false)
    private String category;


    @ColumnDefault("0")
    private int cnt;
    @ColumnDefault("0")
    private int heart;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }

}
