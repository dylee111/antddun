package com.ds.antddun.service;

import com.ds.antddun.dto.JobListDTO;
import com.ds.antddun.entity.JobList;

import java.util.List;

public interface JobListService {

    List<JobList> getList();

//    void saveJob(JobListDTO jobListDTO);


//    default JobListDTO entityToDTO(JobList jobList) {
//
//        JobListDTO jobListDTO = JobListDTO.builder()
//                .jno(jobList.getJno())
//                .job(jobList.getJob())
//                .build();
//        return jobListDTO;
//    }

//    default JobList dtoToEntity(JobListDTO jobListDTO) {
//
//        JobList jobList = JobList.builder()
//                .jno(jobListDTO.getJno())
//                .job(jobListDTO.getJob())
//                .build();
//        return jobList;
//    }
}
