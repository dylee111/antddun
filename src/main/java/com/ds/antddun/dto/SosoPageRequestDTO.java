package com.ds.antddun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// 목록 페이지를 요청할 때 사용하는 데이터를 재사용하기 쉽게 만드는 클래스.
@Builder
@AllArgsConstructor
@Data
public class SosoPageRequestDTO {
    Pageable twentyElements = PageRequest.of(0, 12);
} // class End.
