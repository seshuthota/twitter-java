package com.codepup.twitter.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

public class RateLimitFilter implements HandlerInterceptor {

    private final Bucket bucket;

    public RateLimitFilter() {
        Bandwidth bandwidth = Bandwidth.classic(100, Refill.intervally(10, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(bandwidth)
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Consume a token for each request
        if (bucket.tryConsume(1)) {
            // If token is consumed successfully, allow the request to proceed
            return true;
        } else {
            // If no tokens are left, return a 429 Too Many Requests status
            response.setStatus(429);
            return false;
        }
    }
}
