package com.test.api.marvelapi.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private List<GrantedPermission> Permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public static enum RoleEnum{
        CUSTOMER,
        AUDITOR
    }
}
