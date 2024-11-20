package com.bluebarry.project_managment_sytem.dto.request;

import com.bluebarry.project_managment_sytem.entities.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private Long userId;
    private Long projectId;
    private Date dueDate;
    private TaskStatus status;
}
