package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.mapper.TaskRequestMapper;
import com.nasr.TaskNS.mapper.TaskResponseMapper;
import com.nasr.TaskNS.repository.TaskRepository;
import com.nasr.TaskNS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

//        Users user = userRepository.findById(taskDTO.getAssignedToUserId()).get();
        Users user = userRepository.findByUsername(taskDTO.getClientUserName()).get();
        // set the task payload information
        task.setSubject(taskDTO.getSubject());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(taskDTO.getStatus());
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
//    public List<Tasks> getTasksByParameters(String parameters) {
//        return taskRepository.findAllBySubjectAndDueDateAfterAndStatusOrAssigned(parameters);
//
//    }

    @Override
    public Tasks editTask(TaskRequest taskRequest) {
        Tasks taskFromDB = taskRepository.findTasksById(taskRequest.getId());
        Long userId = taskFromDB.getUserId();
        Users initialUser = userRepository.findById(userId).get();

        taskFromDB.setSubject(taskRequest.getSubject() == null ? taskFromDB.getSubject()
                : taskRequest.getSubject());
        taskFromDB.setDueDate(taskRequest.getDueDate() == null ? taskFromDB.getDueDate()
                : taskRequest.getDueDate());
        taskFromDB.setStatus(taskRequest.getStatus() == null ? taskFromDB.getStatus()
                : taskRequest.getStatus());


        if (!initialUser.getUsername().equals(taskRequest.getClientUserName())) {
            initialUser.removeTaskFromAssignedTasks(taskFromDB);
            userRepository.save(initialUser);
            Users currentUser = userRepository.findByUsername(taskRequest.getClientUserName()).get();
            currentUser.addTaskToUser(taskFromDB);
            userRepository.save(currentUser);
            taskFromDB.setAssigned(currentUser);
        }

        taskRepository.save(taskFromDB);

        return taskFromDB;
    }

    @Override
    public Tasks getTasksById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @Override
    public void deleteTaskById(Long id) {
        Tasks taskFromDB = taskRepository.findTasksById(id);
        Users assignedUser = userRepository.findById(taskFromDB.getAssigned().getId())
                .orElseThrow(() -> new RuntimeException("User not fond in DB"));
        assignedUser.removeTaskFromAssignedTasks(taskFromDB);
        userRepository.save(assignedUser);

        taskRepository.deleteById(id);
        log.info("Task Deleted successfully");
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
