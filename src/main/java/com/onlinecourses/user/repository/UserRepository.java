package com.onlinecourses.user.repository;

import com.onlinecourses.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailIgnoreCase(String s);

    User findByEmail(String username);
}
