package com.nasr.TaskNS.controller;


import com.nasr.TaskNS.dto.SearchRequestDto;
import com.nasr.TaskNS.dto.TaskRequest;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/task")
@CrossOrigin(value = {"*"})
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Tasks> addTask(@RequestBody TaskRequest taskRequest) {
        Tasks addedTask = taskService.createTask(taskRequest);
        return new ResponseEntity<>(addedTask, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tasks>> getAllTasks() {
        List<Tasks> allTasksOrdered = taskService.getAllTasks();
        return new ResponseEntity<>(allTasksOrdered, HttpStatus.OK);
    }


    @GetMapping("/all/{email}")
    public ResponseEntity<List<Tasks>> getTasksByEmail(@PathVariable String email) {
        List<Tasks> allTasksOrdered = taskService.getTasksByEmail(email);
        return new ResponseEntity<>(allTasksOrdered, HttpStatus.OK);
    }

    @GetMapping("/task-details/{id}")
    public ResponseEntity<Tasks> getTasksById(@PathVariable Long id) {
        Tasks taskById = taskService.getTasksById(id);
        return new ResponseEntity<>(taskById, HttpStatus.OK);
    }

    // search
    @PostMapping("/search")
    public ResponseEntity<List<Tasks>> search(@RequestBody SearchRequestDto taskDto) {
        List<Tasks> returnedTasks = taskService.search(taskDto);
        return new ResponseEntity<>(returnedTasks, HttpStatus.OK);
    }



    @PatchMapping("/edit")
    public ResponseEntity<Tasks> editTask(@RequestBody TaskRequest taskRequest) {
        Tasks editedTask = taskService.editTask(taskRequest);
        return new ResponseEntity<>(editedTask, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
