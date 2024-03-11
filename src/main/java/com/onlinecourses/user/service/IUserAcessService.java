package com.onlinecourses.user.service;

import com.onlinecourses.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserAcessService {
    UserDetails loadUserByUsername(String trim);

    User findByEmail(String username);
}
