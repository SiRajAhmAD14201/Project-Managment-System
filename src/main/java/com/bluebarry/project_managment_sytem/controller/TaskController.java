package com.bluebarry.project_managment_sytem.controller;

import com.bluebarry.project_managment_sytem.dto.request.TaskRequest;
import com.bluebarry.project_managment_sytem.dto.response.TaskResponse;
import com.bluebarry.project_managment_sytem.entities.Task;
import com.bluebarry.project_managment_sytem.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTask() {
        List<TaskResponse> taskResponses = taskService.getAllTask();
        return new ResponseEntity<>(taskResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        TaskResponse task = taskService.updateTask(id, taskRequest);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> savaTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse task = taskService.saveTask(taskRequest);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/assign")
    public TaskResponse assignTask(@RequestBody TaskRequest task, @RequestParam String[] userEmail, @RequestParam String attachmentPath) {
        return taskService.assignTask(task, userEmail, attachmentPath);
    }

}
