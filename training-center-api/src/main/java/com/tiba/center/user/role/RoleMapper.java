package com.tiba.center.user.role;

import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

  public Role toRole(RoleRequest request) {
    Role role = new Role();
    role.setName(request.name());
    role.setDescription(request.description());
    return role;
  }

  public RoleResponse toRoleResponse(Role role) {
    return RoleResponse.builder().name(role.getName()).description(role.getDescription()).build();
  }
}
