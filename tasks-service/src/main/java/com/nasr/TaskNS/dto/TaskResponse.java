package com.nasr.TaskNS.dto;

import com.nasr.TaskNS.entity.Status;
import com.nasr.TaskNS.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private Long id;
    private String subject;
    private Date dueDate;
    private Status status;
    private Users assigned;
}
