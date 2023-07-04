package com.coup.service;

import com.coup.domain.Project;
import com.coup.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    /**
    * 프로젝트 등록 (projectId)
    * return Project.Id
    * */
    @Transactional
    public Long create(Project project) {

        validateDuplicateProject(project);
        projectRepository.save(project);
        return project.getId();
    }

    /**
     * 프로젝트 등록 (project info)
     * return Project
     * */
    @Transactional
    public Project createProject(Project project) {
        validateDuplicateProject(project);
        projectRepository.save(project);
        Long projectId = project.getId();
        return projectRepository.findOne(projectId);
    }

    /**
    * 중복프로젝트 확인
    * */
    private void validateDuplicateProject(Project project) {
        List<Project> findProjects = projectRepository.findByProjectName(project.getProjectname());
        if(!findProjects.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 프로젝트 이름입니다.");
        }
    }

    /**
    * 프로젝트 전체 조회
    * */
    public List<Project> findProjects(){
        return projectRepository.findAll();
    }

    /**
     * Id로 프로젝트 조회
     * */
    public Project findOne(Long projectId) { return projectRepository.findOne(projectId); }

    /**
     * 업데이트
     * */
    @Transactional
    public void update(Long id, String projectname) {
        Project project = projectRepository.findOne(id);
        project.setProjectname(projectname);
    }
}
