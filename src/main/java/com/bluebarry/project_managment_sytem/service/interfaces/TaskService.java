package com.bluebarry.project_managment_sytem.service.interfaces;

import com.bluebarry.project_managment_sytem.dto.request.TaskRequest;
import com.bluebarry.project_managment_sytem.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTask();
    TaskResponse getTaskById(Long id);
    TaskResponse updateTask(Long id,TaskRequest taskRequest);
    TaskResponse saveTask(TaskRequest taskRequest);
    void deleteTask(Long id);
    TaskResponse assignTask(TaskRequest task, String[] userEmail, String attachmentPath);

}
