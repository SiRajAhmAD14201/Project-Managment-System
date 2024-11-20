package com.bluebarry.project_managment_sytem.service.implementation;

import com.bluebarry.project_managment_sytem.dto.request.ProjectRequest;
import com.bluebarry.project_managment_sytem.dto.response.ProjectResponse;
import com.bluebarry.project_managment_sytem.entities.Project;
import com.bluebarry.project_managment_sytem.entities.Team;
import com.bluebarry.project_managment_sytem.entities.User;
import com.bluebarry.project_managment_sytem.exception.EntityNotFoundException;
import com.bluebarry.project_managment_sytem.repository.ProjectRepo;
import com.bluebarry.project_managment_sytem.repository.TeamRepo;
import com.bluebarry.project_managment_sytem.repository.UserRepo;
import com.bluebarry.project_managment_sytem.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    private final TeamRepo teamRepo;
    private final UserRepo userRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo, TeamRepo teamRepo, UserRepo userRepo) {
        this.projectRepo = projectRepo;
        this.teamRepo = teamRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<ProjectResponse> getAllProject() {
        return projectRepo.findAll().stream().map(this::toProjectResponse).collect(Collectors.toList());
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
        return toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
//
////        if (projectRequest.getUserIds()!=null){
////            User user=userRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Not found user against this id:"+id));
////        }
////        if (projectRequest.getTeamIds()!=null){
////            Team team=teamRepo.findById(id).orElseThrow(()-> new EntityNotFoundException((""+id)));
//
//        }

        Project updatedProject = projectRepo.save(project);
        return toProjectResponse(updatedProject);
    }

    private ProjectResponse toProjectResponse(Project project) {
        ProjectResponse projectResponse=new ProjectResponse();
        projectResponse.setId(project.getId());
        projectResponse.setName(project.getName());
        projectResponse.setDescription(project.getDescription());
        return projectResponse;

    }

    @Override
    public ProjectResponse saveProject(ProjectRequest projectRequest) {
        Project project=new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        Project savedProject=projectRepo.save(project);
        return toProjectResponse(savedProject);
    }


    @Override
    public void deleteProject(Long id) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + id));
        projectRepo.delete(project);

    }



}