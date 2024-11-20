package com.bluebarry.project_managment_sytem.dto.response;

import com.bluebarry.project_managment_sytem.entities.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private Long UserId;
    private Long projectId;
    private TaskStatus status;
}
