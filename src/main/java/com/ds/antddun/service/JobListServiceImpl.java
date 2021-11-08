package com.ds.antddun.service;

import com.ds.antddun.entity.JobList;
import com.ds.antddun.repository.JobListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobListServiceImpl implements JobListService {

    private final JobListRepository jobListRepository;

    @Override
    public List<JobList> getList() {

//        JobListDTO result = entityToDTO(jobList);
        return jobListRepository.findAll();
    }

//    @Override
//    public void saveJob(JobListDTO jobListDTO) {
//        JobList jobList = jobListRepository.findById(jobListDTO.getJno()).get();
//
//
//    }
}
