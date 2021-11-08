package com.ds.antddun.service;

import com.ds.antddun.repository.SosoBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SosoJobServiceImpl implements SosoJonService {

    @Autowired
    private SosoBoardRepository sosoBoardRepository;

    @Override
    public void register() {
    }

    @Override
    public void modify() {

    }

    @Override
    public void delete() {

    }
}
