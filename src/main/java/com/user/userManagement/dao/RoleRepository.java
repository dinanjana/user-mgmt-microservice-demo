package com.user.userManagement.dao;

import com.user.userManagement.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dinanjanag on 3/20/19.
 */
@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Role getRoleById(Long id);
}
