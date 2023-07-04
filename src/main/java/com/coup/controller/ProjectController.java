package com.coup.controller;

import com.coup.domain.Project;
import com.coup.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) { this.projectService = projectService; }

    @GetMapping(path = "/testProject") //http://localhost:8080/testProject
    public String test() { return "test success"; }

    @GetMapping("/projects")
    public List<Project> retrieveAllProjects() {
        return projectService.findProjects();
    }

    // Get /project/1 or project/10 -> String
    @GetMapping("/projects/{id}")
    public Project retrieveProject(@PathVariable Long id) { return projectService.findOne(id); }

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

}