package com.bluebarry.project_managment_sytem.service.implementation;

import com.bluebarry.project_managment_sytem.dto.request.UserRequest;
import com.bluebarry.project_managment_sytem.dto.response.UserResponse;
import com.bluebarry.project_managment_sytem.entities.Team;
import com.bluebarry.project_managment_sytem.entities.User;
import com.bluebarry.project_managment_sytem.exception.EntityNotFoundException;
import com.bluebarry.project_managment_sytem.repository.ProjectRepo;
import com.bluebarry.project_managment_sytem.repository.TaskRepo;
import com.bluebarry.project_managment_sytem.repository.TeamRepo;
import com.bluebarry.project_managment_sytem.repository.UserRepo;
import com.bluebarry.project_managment_sytem.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;
    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TeamRepo teamRepo, TaskRepo taskRepo, ProjectRepo projectRepo) {
        this.userRepo = userRepo;
        this.teamRepo = teamRepo;
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepo.findAll().stream().map(this::toUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {
       return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
//
//        // Set teams if `teamIds` are provided in `userRequest`
//        if (userRequest.getTeamIds() != null) {
//            List<Team> teams = userRequest.getTeamIds().stream()
//                    .map(teamId -> teamRepo.findById(teamId)
//                            .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId)))
//                    .collect(Collectors.toList());
//            user.setTeams(teams);
//        }

        User savedUser = userRepo.save(user);
        return toUserResponse(savedUser);
    }


    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());

        // Update teams if `teamIds` are provided in `userRequest`
//        if (userRequest.getTeamIds() != null) {
//            List<Team> teams = userRequest.getTeamIds().stream()
//                    .map(teamId -> teamRepo.findById(teamId)
//                            .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId)))
//                    .collect(Collectors.toList());
//            user.setTeams(teams);
//        }

        User updatedUser = userRepo.save(user);
        return toUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userRepo.delete(user);

    }
}
