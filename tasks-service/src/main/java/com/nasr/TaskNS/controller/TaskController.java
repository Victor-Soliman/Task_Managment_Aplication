package com.nasr.TaskNS.controller;


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

    //    @PostMapping("/add")
//    public ResponseEntity<Tasks> addTask(@RequestBody TaskRequest taskRequest) {
//        Tasks addedTask = taskService.addTask(taskRequest);
//        return new ResponseEntity<>(addedTask, HttpStatus.OK);
//    }
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

//    @GetMapping("/all/{email}")
//    public ResponseEntity<List<Tasks>> getTasksByEmail(@PathVariable String email) {
//        List<Tasks> allTasksOrdered = taskService.getTasksByEmail(email);
//        return new ResponseEntity<>(allTasksOrdered, HttpStatus.OK);
//    }
    @GetMapping("/all/{email}")
    public List<Tasks> getTasksByEmail(@PathVariable String email) {
        List<Tasks> allTasksOrdered = taskService.getTasksByEmail(email);
        return allTasksOrdered;
    }


}
