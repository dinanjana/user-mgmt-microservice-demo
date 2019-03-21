package com.user.userManagement.dao;

import com.user.userManagement.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinanjanag on 3/20/19.
 */
@Repository
public interface UserRepository extends JpaRepository <User, String>{

    /**
     * Fetch user by user name
     * @param userName: User name
     * @return  fetched user or null
     * */
    User findUserByUserName(String userName);

}
