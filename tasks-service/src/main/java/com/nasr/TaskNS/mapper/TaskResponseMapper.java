package com.nasr.TaskNS.mapper;

import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.dto.TaskResponse;
import com.nasr.TaskNS.entity.Tasks;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseMapper {

    public TaskResponse fromTaskEntityToResponseDto(Tasks task) {
        return TaskResponse.builder()
                .id(task.getId())
                .subject(task.getSubject())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .assigned(task.getAssigned())
                .build();
    }

    public Tasks fromResponseDtoToTaskEntity(TaskResponse task) {
        return Tasks.builder()
                .id(task.getId())
                .subject(task.getSubject())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .assigned(task.getAssigned())
                .build();
    }

}
