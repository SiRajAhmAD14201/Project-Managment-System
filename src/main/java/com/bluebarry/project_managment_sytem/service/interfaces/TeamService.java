package com.bluebarry.project_managment_sytem.service.interfaces;

import com.bluebarry.project_managment_sytem.dto.request.TeamRequest;
import com.bluebarry.project_managment_sytem.dto.response.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> getAllTeam();
    TeamResponse getTeamById(Long id);
    TeamResponse saveTeam(TeamRequest teamRequest);
    TeamResponse updateTeam(Long id,TeamRequest teamRequest);
    void deleteTeam(Long id);
}
