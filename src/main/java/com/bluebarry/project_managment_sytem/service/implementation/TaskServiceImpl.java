package com.bluebarry.project_managment_sytem.service.implementation;

import com.bluebarry.project_managment_sytem.dto.request.TaskRequest;
import com.bluebarry.project_managment_sytem.dto.response.TaskResponse;
import com.bluebarry.project_managment_sytem.entities.Project;
import com.bluebarry.project_managment_sytem.entities.Task;
import com.bluebarry.project_managment_sytem.entities.User;
import com.bluebarry.project_managment_sytem.exception.EntityNotFoundException;
import com.bluebarry.project_managment_sytem.repository.ProjectRepo;
import com.bluebarry.project_managment_sytem.repository.TaskRepo;
import com.bluebarry.project_managment_sytem.repository.UserRepo;
import com.bluebarry.project_managment_sytem.service.interfaces.EmailService;
import com.bluebarry.project_managment_sytem.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;
    private final EmailService emailService;
@Autowired
    public TaskServiceImpl(TaskRepo taskRepo, ProjectRepo projectRepo, UserRepo userRepo, EmailService emailService) {
        this.taskRepo = taskRepo;
    this.projectRepo = projectRepo;
    this.userRepo = userRepo;
    this.emailService = emailService;

}

    @Override
    public List<TaskResponse> getAllTask() {
        return taskRepo.findAll().stream().map(this::toTaskResponse).collect(Collectors.toList());

}


    @Override
    public TaskResponse getTaskById(Long id) {
        Task task=taskRepo.findById(id).orElseThrow(()->new EntityNotFoundException("task not found with this Id: " + id));
        return toTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setStatus(taskRequest.getStatus());

        if (taskRequest.getUserId() != null) {
            User assignedUser = getUserById(taskRequest.getUserId());
            task.setAssignedUser(assignedUser);
        }

        if (taskRequest.getProjectId() != null) {
            Project project = getProjectById(taskRequest.getProjectId());
            task.setProject(project);
        }

        Task updatedTask = taskRepo.save(task);
        return toTaskResponse(updatedTask);
    }

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setStatus(taskRequest.getStatus());

        if (taskRequest.getUserId() != null) {
            User assignedUser = getUserById(taskRequest.getUserId());
            task.setAssignedUser(assignedUser);
        }

        if (taskRequest.getProjectId() != null) {
            Project project = getProjectById(taskRequest.getProjectId());
            task.setProject(project);
        }

        Task savedTask = taskRepo.save(task);
        return toTaskResponse(savedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));
        taskRepo.delete(task);
    }

    private TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getAssignedUser().getId(),
                task.getProject().getId(),
                task.getStatus()
        );
    }
    private User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }
    private Project getProjectById(Long projectId) {
        return projectRepo.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
    }

    @Override
    public TaskResponse assignTask(TaskRequest taskRequest, String[] userEmail, String attachmentPath) {
        // Convert TaskRequest to Task entity
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setStatus(taskRequest.getStatus());

        if (taskRequest.getUserId() != null) {
            User assignedUser = getUserById(taskRequest.getUserId());
            task.setAssignedUser(assignedUser);
        }

        if (taskRequest.getProjectId() != null) {
            Project project = getProjectById(taskRequest.getProjectId());
            task.setProject(project);
        }

        // Save the task
        Task savedTask = taskRepo.save(task);

        // Prepare and send an email
        String subject = "New Task Available: " + task.getTitle();
        String body = "A new task is available:\n\n" +
                "Title: " + task.getTitle() + "\n" +
                "Description: " + task.getDescription() + "\n" +
                "Deadline: " + task.getDueDate() + "\n\n" +
                "Please review the task and assign it to an available team member.";

        emailService.sendEmailWithAttachment(userEmail, subject, body, attachmentPath);

        // Convert saved Task to TaskResponse and return
        return toTaskResponse(savedTask);
    }

}
