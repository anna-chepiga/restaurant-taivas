package ua.goit.dao;

import ua.goit.domain.UserRole;

public interface UserRoleDao {
    UserRole findByName(String roleName);
}