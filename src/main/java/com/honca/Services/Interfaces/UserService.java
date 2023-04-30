package com.honca.Services.Interfaces;

import com.honca.Models.User;

public interface UserService {
    User findUserByUsername(String username);
}
