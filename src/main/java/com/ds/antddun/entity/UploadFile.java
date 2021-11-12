package com.ds.antddun.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(name="IMAGE_SEQ_GEN", sequenceName = "IMAGE_SEQ", initialValue = 1, allocationSize = 1)
public class UploadFile extends BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOSOIMAGE_SEQ_GEN")
	private Long imageNo;

	@Column
	private String fileName;
	@Column
	private String saveFileName;
	@Column
	private String filePath;
	@Column
	private String contentType;
	
	private long size;
}
