package com.ds.antddun.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(name = "IMG_SEQ_GEN", sequenceName = "IMG_SEQ", initialValue = 1, allocationSize = 1)
public class UploadFile extends BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMG_SEQ_GEN")
	private Long inum;
	
	@Column
	private String fileName;
	@Column
	private String saveFileName;
	@Column
	private String filePath;
	@Column
	private String contentType;
	
	private long size;

	@ManyToOne(fetch = FetchType.LAZY)
	private JayuBoard jayuBoard;

	@ManyToOne(fetch = FetchType.LAZY)
	private QnaBoard qnaBoard;

	@ManyToOne(fetch = FetchType.LAZY)
	private SosoJobBoard sosoJobBoard;

}
