package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.SearchRequestDto;
import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.entity.Users;
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

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public Tasks createTask(TaskRequest taskDTO) {
        Tasks task = new Tasks();
        // get the user from db based on id , you don't need to send the hole user
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

    @Override
    public List<Tasks> search(SearchRequestDto taskDto) {
        return taskRepository.findBySearch(
                taskDto.getSubject(),
                taskDto.getDueDate(),
                taskDto.getStatus(),
                taskDto.getClientUserName());
    }
}
