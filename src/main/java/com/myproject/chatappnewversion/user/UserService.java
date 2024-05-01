package com.myproject.chatappnewversion.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnect(User user) {
        var stored_user = userRepository.findById(user.getNickName())
                .orElse(null);
        if (stored_user != null) {
            stored_user.setStatus(Status.OFFLINE);
            userRepository.save(stored_user);
        }
    }

    public List<User> findConnectedUser() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }

}
