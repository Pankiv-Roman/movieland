package com.pankiv.movieland.service;

import com.pankiv.movieland.entity.User;

public interface UserService {

    User findByEmail(String email);
}
