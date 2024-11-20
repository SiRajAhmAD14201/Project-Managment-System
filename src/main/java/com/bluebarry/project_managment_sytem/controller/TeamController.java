package com.bluebarry.project_managment_sytem.controller;

import com.bluebarry.project_managment_sytem.dto.request.TeamRequest;
import com.bluebarry.project_managment_sytem.dto.response.TeamResponse;
import com.bluebarry.project_managment_sytem.service.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeam() {
        List<TeamResponse> teamResponses = teamService.getAllTeam();
        return new ResponseEntity<>(teamResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id) {
        TeamResponse teamResponse = teamService.getTeamById(id);
        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamResponse> saveTeam(@RequestBody TeamRequest teamRequest) {
        TeamResponse teamRequest1 = teamService.saveTeam(teamRequest);
        return new ResponseEntity<>(teamRequest1, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeam(@PathVariable Long id, @RequestBody TeamRequest teamRequest) {
        TeamResponse teamRequest1 = teamService.updateTeam(id, teamRequest);
        return new ResponseEntity<>(teamRequest1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
