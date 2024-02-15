package com.test.api.marvelapi.persistence.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
public class Role implements GrantedAuthority {

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

    @Override
    public String getAuthority() {
        if(name == null){
            return null;
        }
        return "ROLE_"+name.name();
    }

    public List<GrantedPermission> getPermissions() {
        return Permissions;
    }

    public void setPermissions(List<GrantedPermission> permissions) {
        Permissions = permissions;
    }

    public static enum RoleEnum{
        CUSTOMER,
        AUDITOR
    }
}
