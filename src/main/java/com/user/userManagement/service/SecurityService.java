package com.user.userManagement.service;

import org.springframework.stereotype.Service;

/**
 * Created by dinanjanag on 3/20/19.
 */
public interface SecurityService {

    String findLoggedInUsername();
    boolean login(String username, String password);

}
