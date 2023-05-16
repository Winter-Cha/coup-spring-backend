package com.coup.domain;

import com.coup.domain.type.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_PWD")
    private String pwd;

    //@Column(name = "name", columnDefinition = "varchar(100) default 'EMPTY'")
    @Column(name = "USER_NAME", unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE_TYPE")
    private RoleType roleType;

    private String userSts;

    private String email;

//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
//
//    @ManyToOne
//    @JoinColumn(name = "PROJECT_ID")
//    private Project project;


    //private BigDecimal age;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "REG_DT")
    //private Date createdDate;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "CHG_DT")
    //private Date lastModifiedDate;

    //@Lob
    //private String description;

    //@Transient
    //private int temp;



}
