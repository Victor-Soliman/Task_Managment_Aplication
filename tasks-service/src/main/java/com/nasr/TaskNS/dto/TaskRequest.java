package com.nasr.TaskNS.dto;

import com.nasr.TaskNS.entity.Status;
import com.nasr.TaskNS.entity.Users;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter


public class TaskRequest {


    private String subject;
    private Date dueDate;
    private Status status;
    private Long assignedToUserId;

    public TaskRequest(Long assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public TaskRequest(String subject,
                       Date dueDate,
                       Status status,
                       Long assignedToUserId) {
        this.subject = subject;
        this.dueDate = dueDate;
        this.status = status;
        this.assignedToUserId = assignedToUserId;
    }
}
