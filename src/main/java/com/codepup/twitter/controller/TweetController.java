package com.codepup.twitter.controller;

import com.codepup.twitter.model.Tweet;
import com.codepup.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller("/api/")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping("/save")
    public ResponseEntity<Tweet> registerTweet(@RequestBody Tweet Tweet) {
        Tweet savedTweet = tweetService.saveTweet(Tweet);
        return ResponseEntity.ok(savedTweet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable Long id) {
        Optional<Tweet> Tweet = tweetService.findTweetById(id);
        if (Tweet.isPresent()) {
            return ResponseEntity.ok(Tweet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

