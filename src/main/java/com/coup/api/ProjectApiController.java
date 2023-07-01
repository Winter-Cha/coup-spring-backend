package com.coup.api;

import com.coup.domain.Project;
import com.coup.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProjectApiController {
    private final ProjectService projectService;

    @GetMapping("/api/projects")
    public Result retrieveProjects() {
        List<Project> findProjects = projectService.findProjects();

        List<ProjectDto> collect = findProjects.stream()
                .map(m -> new ProjectDto(m.getProjectname(), m.getProjectnickname()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ProjectDto {
        private String projectname;
        private String projectnickname;
    }

    //  /api/v2/project
    @PostMapping("/api/members")
    public CreateProjectResponse saveProjectV2(@RequestBody @Valid CreateProjectRequest request) {
        Project project = new Project();
        project.setProjectname(request.getProjectname());
        project.setProjectnickname(request.getProjectnickname());

        Long id = projectService.create(project);

        return new CreateProjectResponse(id);
    }

    @Data
    static class CreateProjectRequest {
        @NotEmpty
        private String projectname;
        private String projectnickname;

    }

    @Data
    static class CreateProjectResponse {
        private Long id;

        public CreateProjectResponse(Long id) { this.id = id; }
    }

}
