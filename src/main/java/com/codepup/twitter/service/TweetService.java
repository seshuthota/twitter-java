package com.codepup.twitter.service;

import com.codepup.twitter.model.Tweet;
import com.codepup.twitter.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    public Tweet saveTweet(Tweet Tweet) {
        return tweetRepository.save(Tweet);
    }

    public Optional<Tweet> findTweetById(Long id) {
        return tweetRepository.findById(id);
    }

    public List<Tweet> findUserById(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    public Tweet likeTweet(Long tweetId) {
        // Fetch the tweet, increment its like count, then save it
        Tweet tweet = tweetRepository.findById(tweetId).orElse(null);
        if (tweet != null) {
            tweet.setLikesCount(tweet.getLikesCount() + 1);
            tweetRepository.save(tweet);
        }
        return tweet;
    }


}
