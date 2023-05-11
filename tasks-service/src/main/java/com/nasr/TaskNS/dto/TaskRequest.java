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

    public TaskRequest(Long userId) {
        this.userId = userId;
    }

    public TaskRequest(String subject,
                       Date dueDate,
                       Status status,
                       Long userId) {
        this.subject = subject;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
    }

    public TaskRequest(Long id, String subject, Date dueDate, Status status, Long userId) {
        this.id = id;
        this.subject = subject;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
    }
}
