package com.user.userManagement.service;

/**
 * Created by dinanjanag on 3/20/19.
 */
public interface SecurityService {

    String findLoggedInUsername();
    void autoLogin(String username, String password);

}
