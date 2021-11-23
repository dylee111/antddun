package com.ds.antddun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Builder
@AllArgsConstructor
@Data

public class JayuPageRequestDTO {
    Pageable twentyElements = PageRequest.of(0, 9);
}
