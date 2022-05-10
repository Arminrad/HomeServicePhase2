package com.phase2.homeService.repository;

import com.phase2.homeService.entities.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByEmail(String email);
}
