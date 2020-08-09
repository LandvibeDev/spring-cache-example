package com.landvibe.cacheexample;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private Map<Integer, User> users = new HashMap<>();

    public UserRepository() {
    }

    public Optional<User> findUser(int uid) {
        return Optional.ofNullable(users.get(uid));
    }

    public void saveUser(User user) {
        findUser(user.getId()).ifPresent(u -> users.remove(u.getId()));
        users.put(user.getId(), user);
    }
}
