package com.bluebarry.project_managment_sytem.service.interfaces;

import com.bluebarry.project_managment_sytem.dto.request.ProjectRequest;
import com.bluebarry.project_managment_sytem.dto.response.ProjectResponse;

import java.util.List;


public interface ProjectService {
    List<ProjectResponse> getAllProject();
    ProjectResponse getProjectById(Long id);
    ProjectResponse updateProject(Long id,ProjectRequest projectRequest);
    ProjectResponse saveProject(ProjectRequest projectRequest);
    void deleteProject(Long id);
}
