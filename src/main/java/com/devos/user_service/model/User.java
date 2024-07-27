package com.devos.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Data
@Entity
@Table(name="users",schema = "public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String pwd;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;


    @ManyToMany()
    @JsonIgnoreProperties("roles")
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Permission> permissions=new HashSet<Permission>();
        List<Role> roles=  this.getRoles();
        for( Role role :roles){
            permissions.addAll(role.getPermissions());
        }
        return permissions;
    }

    @Override
    public String getPassword() {
        return pwd;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // Getters and setters
}
