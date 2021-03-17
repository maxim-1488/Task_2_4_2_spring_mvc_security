package ru.maximkrylov.spring_mvc.dao;

import ru.maximkrylov.spring_mvc.model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    void updateRole(Role role);

    void removeRoleById(long id);

    List<Role> getAllRoles();

}
