package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "sosoJob")
@SequenceGenerator(name="SOSOIMAGE_SEQ_GEN",sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
public class UploadImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOSOIMAGE_SEQ_GEN")
    private Long inum;
    private String uuid;
    private String imgName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private SosoJobBoard sosoJob;
}
