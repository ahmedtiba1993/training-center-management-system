package com.tiba.center.user.user;

public interface UserService {

  void changePasswordAtFirstLogin(Long userConnectedId, String newPassword);

  User getUserById(Long userId);

  boolean isMustChangePasswordFirstLogin(Long userConnectedId);

  void adminChangeUserPassword(AdminChangePasswordRequest request);
}
