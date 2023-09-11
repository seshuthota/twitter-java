package com.codepup.twitter.service;

import com.codepup.twitter.model.Follow;
import com.codepup.twitter.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    public Follow saveUser(Follow follow) {
        return followRepository.save(follow);
    }

    public Optional<Follow> findUserById(Long id) {
        return followRepository.findById(id);
    }

}
