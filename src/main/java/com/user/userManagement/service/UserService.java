package com.user.userManagement.service;

import com.user.userManagement.beans.User;
import com.user.userManagement.dao.RoleRepository;
import com.user.userManagement.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by dinanjanag on 3/20/19.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        User userCopy = new User(user);
        userCopy.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userCopy.setRole(roleRepository.getRoleById(user.getRole().getId()));
        userRepository.save(userCopy);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public User getUserByUserName (String userName) {
        return userRepository.findUserByUserName(userName);
    }

}
