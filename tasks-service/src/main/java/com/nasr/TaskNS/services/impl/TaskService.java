package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.dto.TaskResponse;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.mapper.TaskRequestMapper;
import com.nasr.TaskNS.mapper.TaskResponseMapper;
import com.nasr.TaskNS.repository.TaskRepository;
import com.nasr.TaskNS.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService implements com.nasr.TaskNS.services.TaskService {

    private final TaskRequestMapper taskRequestMapper;
    private final TaskRepository taskRepository;
    private final TaskResponseMapper taskResponseMapper;
    private final UserRepository userRepository;
    private final UserService userService;

//    @Override
//    public TaskResponse addTask(TaskRequest taskRequest) {
//        Tasks taskToAdd = taskRequestMapper.fromRequestDtoToTaskEntity(taskRequest);
//        // assign to user
//
//        Users user = taskRequest.getAssigned();
//        Users userToAssign = userRepository.findById(taskRequest.getAssigned().getId())
//                .orElseThrow(() -> new EntityNotFoundException("\"User not found in the DB to assign to this task\""));
//
//        // add this task to the task list
//        userToAssign.getTasks().add(taskToAdd);
//
////        userRepository.save(userToAssign);
//        Tasks saved = taskRepository.save(taskToAdd);
////        Users updated = userRepository.save(userToAssign);
//        return taskResponseMapper.fromTaskEntityToResponseDto(saved);
//    }

    @Override
    public Tasks createTask(TaskRequest taskDTO) {
        Tasks task = new Tasks();
        // get the user from db based on id , you don't need to send the hole user

        Users user = userRepository.findById(taskDTO.getAssignedToUserId()).get();
        // set the task payload information
        task.setSubject(taskDTO.getSubject());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());
        task.setAssigned(user);

        taskRepository.save(task); // save task in db
        user.addTaskToUser(task); // add the task to user
        userRepository.save(user); // save the updated user in the db

        return task;
    }

    @Override
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Tasks> getTasksByEmail(String email) {
        Users userByEmail = userService.getUserByEmail(email);
        return taskRepository.findAllTasksForUser(userByEmail.getId());
    }

//    @Override
//    public List<Tasks> getAllTasksOrdered(Long user_id) {
////        Long assignedToUserId = userRepository.findById(user_id);
//        return taskRepository.findAllByAssignedUserId(user_id);
//
//    }

//    @Override
//    public Tasks addTask(Tasks taskDTO) {
//        Tasks task = new Tasks();
//        // get the user from db based on id , you don't need to send the hole user
//        Users user = userRepository.findById(taskDTO.getUserId()).get();
//        // set the task payload information
//        task.setSubject(taskDTO.getSubject());
//        task.setStatus(taskDTO.getStatus());
//        task.setDueDate(taskDTO.getDueDate());
//        task.setAssigned(user);
//
//        taskRepository.save(task); // save task in db
//        user.addTaskToUser(task); // add the task to user
//        userRepository.save(user); // save the updated user in the db
//
//        return task;
//    }

}
