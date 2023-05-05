package com.nasr.TaskNS.mapper;

import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.entity.Users;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskRequestMapper {

//    public TaskRequest fromTaskEntityToRequestDto(Tasks task){
//        return TaskRequest.builder()
//                .subject(task.getSubject())
//                .dueDate(task.getDueDate())
//                .status(task.getStatus())
//                .assigned(task.getAssigned())
//                .build();
//    }
//
//    public Tasks fromRequestDtoToTaskEntity(TaskRequest task){
//        return Tasks.builder()
//                .subject(task.getSubject())
//                .dueDate(task.getDueDate())
//                .status(task.getStatus())
//                .assigned(task.getAssigned())
//                .build();
//    }
}
