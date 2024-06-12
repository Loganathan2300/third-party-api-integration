package com.integration.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integration.api.entity.User;

@Repository
public interface UserReposity extends JpaRepository<User, Long> {

}
