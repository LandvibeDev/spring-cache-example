package com.landvibe.cacheexample;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "user", key = "#uid")
    public Optional<User> getUser(int uid) {
        return userRepository.findUser(uid);
    }

    @CacheEvict(value = "user", key = "#user.id")
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }
}
