package com.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
