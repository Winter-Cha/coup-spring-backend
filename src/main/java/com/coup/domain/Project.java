package com.coup.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter @Setter
public class Project extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "PROJECT_ID")
    private Long Id;

    @OneToMany(mappedBy = "project")
    private List<Member> member = new ArrayList<>();

    @Column(name = "PROJECT_NM")
    private String name;







}
