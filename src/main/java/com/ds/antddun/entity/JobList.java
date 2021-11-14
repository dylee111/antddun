package com.ds.antddun.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="JOBLIST_SEQ_GEN", sequenceName = "JOBLIST_SEQ", initialValue = 1)
public class JobList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOBLIST_SEQ_GEN")
    private Long jno;

    private String job;

}
