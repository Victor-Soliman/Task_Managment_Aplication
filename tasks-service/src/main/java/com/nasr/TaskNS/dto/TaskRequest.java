package com.nasr.TaskNS.dto;

import com.nasr.TaskNS.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

public class TaskRequest {

    private Long id;
    @NotNull
    private String subject;
    @NotNull
    private Date dueDate;
    @NotNull
    private Status status;
    private Long userId;
    private String clientUserName;

}
