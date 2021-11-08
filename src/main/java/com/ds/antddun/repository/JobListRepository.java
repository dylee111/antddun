package com.ds.antddun.repository;

import com.ds.antddun.dto.JobListDTO;
import com.ds.antddun.entity.JobList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobListRepository extends JpaRepository<JobList, Long> {


}
