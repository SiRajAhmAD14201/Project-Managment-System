package com.bluebarry.project_managment_sytem.service.implementation;

import com.bluebarry.project_managment_sytem.dto.request.TeamRequest;
import com.bluebarry.project_managment_sytem.dto.response.TeamResponse;
import com.bluebarry.project_managment_sytem.entities.Project;
import com.bluebarry.project_managment_sytem.entities.Team;
import com.bluebarry.project_managment_sytem.entities.User;
import com.bluebarry.project_managment_sytem.exception.EntityNotFoundException;
import com.bluebarry.project_managment_sytem.repository.ProjectRepo;
import com.bluebarry.project_managment_sytem.repository.TeamRepo;
import com.bluebarry.project_managment_sytem.repository.UserRepo;
import com.bluebarry.project_managment_sytem.service.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepo teamRepo;
    private  final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    @Autowired
    public TeamServiceImpl(TeamRepo teamRepo, UserRepo userRepo, ProjectRepo projectRepo) {

        this.teamRepo = teamRepo;
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
    }

    @Override
    public List<TeamResponse> getAllTeam() {
        return teamRepo.findAll().stream().map(this::toTeamResponse).collect(Collectors.toList());
    }

    @Override
    public TeamResponse getTeamById(Long id) {
        Team team= teamRepo.findById(id).orElseThrow(()->new EntityNotFoundException("team not found with ID: " + id));
        return toTeamResponse(team);

    }

    @Override
    public TeamResponse saveTeam(TeamRequest teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());

        if (teamRequest.getProjectIds() != null) {
            List<Project> projects = teamRequest.getProjectIds().stream()
                    .map(projectId -> projectRepo.findById(projectId)
                            .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId)))
                    .collect(Collectors.toList());
            team.setProjectId(projects);
        }

        // Set members for the team
        if (teamRequest.getMemberIds() != null) {
            List<User> members = teamRequest.getMemberIds().stream()
                    .map(memberId -> userRepo.findById(memberId)
                            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + memberId)))
                    .collect(Collectors.toList());
            team.setMembers(members);
        }

        Team savedTeam = teamRepo.save(team);
        return toTeamResponse(savedTeam);
    }

    @Override
    public TeamResponse updateTeam(Long id, TeamRequest teamRequest) {
        Team team = teamRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + id));

        team.setName(teamRequest.getName());

        if (teamRequest.getProjectIds() != null) {
            Project project=projectRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("not found project against this :"+id));
        }

        if (teamRequest.getMemberIds() != null) {
            Team team1=teamRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("not found members against this id"+id));
        }

        Team updatedTeam = teamRepo.save(team);
        return toTeamResponse(updatedTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        Team team = teamRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + id));
        teamRepo.delete(team);
    }



    private TeamResponse toTeamResponse(Team team) {
        List<Long> projectIds = team.getProjectId() != null
                ? team.getProjectId().stream().map(Project::getId).collect(Collectors.toList())
                : Collections.emptyList();

        List<Long> memberIds = team.getMembers() != null
                ? team.getMembers().stream().map(User::getId).collect(Collectors.toList())
                : Collections.emptyList();

        return new TeamResponse(
                team.getId(),
                team.getName(),
                projectIds,
                memberIds
        );
    }

}
