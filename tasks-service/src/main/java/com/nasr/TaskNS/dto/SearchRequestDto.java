package com.nasr.TaskNS.dto;

import com.nasr.TaskNS.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {

    private String subject;
    private Date dueDate;
    private Status status;
    private String clientUserName;
}
