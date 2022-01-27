package com.qa.springsandbox.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springsandbox.data.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

}
