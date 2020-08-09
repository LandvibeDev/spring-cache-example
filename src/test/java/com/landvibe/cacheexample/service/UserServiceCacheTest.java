package com.landvibe.cacheexample.service;

import com.landvibe.cacheexample.CacheExampleApplication;
import com.landvibe.cacheexample.User;
import com.landvibe.cacheexample.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CacheExampleApplication.class)
public class UserServiceCacheTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    UserService userService;

    @Test
    void test_cache_user() {
        // given
        int uid = 1;
        int notCachedUid = 2;
        User user = new User(uid, "test@naver.com", "test");

        // when
        userService.saveUser(user);
        Optional<User> cachedUser = userService.getUser(uid); // cache

        // then
        assertEquals(of(user), cachedUser);
        assertEquals(cachedUser, getCachedUser(uid));
        assertEquals(empty(), getCachedUser(notCachedUid));
    }

    @Test
    void test_cache_evict_user() {
        // given
        int uid = 1;
        User user = new User(uid, "test@naver.com", "test");
        User evictUser = new User(uid, "newTest@naver.com", "newTest");

        // when
        userService.saveUser(user);
        Optional<User> cachedUser = userService.getUser(uid); // cache
        userService.saveUser(evictUser); // evict

        // then
        assertEquals(of(user), cachedUser);
        assertEquals(empty(), getCachedUser(uid));
    }

    private Optional<User> getCachedUser(int uid) {
        return Optional.ofNullable(cacheManager.getCache("user"))
                .map(u -> u.get(uid, User.class));
    }
}
