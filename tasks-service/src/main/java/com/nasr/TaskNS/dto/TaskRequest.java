package com.nasr.TaskNS.dto;

import com.nasr.TaskNS.entity.Status;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor

public class TaskRequest {

    private Long id;
    private String subject;
    private Date dueDate;
    private Status status;
    private Long userId;
    private String clientUserName;

}
