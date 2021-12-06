package com.ds.antddun.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public interface GroupBySender {
    Long getSendMno();
    Long getBoard();
    String getFirstName();
    String getLastName();
    boolean getTrade();
}
