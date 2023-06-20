package com.coup.repository;

import com.coup.domain.Project;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {
    private final EntityManager em;

    public void save(Project project) {
        if (project.getId() == null) {
            em.persist(project);
        } else {
            em.merge(project);
        }
    }

    public Project findOne(Long id) {
        return em.find(Project.class, id);
    }

    public List<Project> findAll(){
        return em.createQuery("select p from Project p", Project.class).getResultList();
    }

}
