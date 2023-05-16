package com.nasr.TaskNS.services;

import com.nasr.TaskNS.dto.SearchRequestDto;
import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.entity.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    Tasks createTask(TaskRequest taskRequest);

    List<Tasks> getAllTasks();

    List<Tasks> getTasksByEmail(String email);

    Tasks editTask(TaskRequest taskRequest);

    Tasks getTasksById(Long id);

    void deleteTaskById(Long id);

    List<Tasks> search(SearchRequestDto taskDto);
}
