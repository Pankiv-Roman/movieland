package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.User;
import com.pankiv.movieland.repository.UserRepository;
import com.pankiv.movieland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
