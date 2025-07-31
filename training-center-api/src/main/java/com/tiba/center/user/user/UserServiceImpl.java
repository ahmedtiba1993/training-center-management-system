package com.tiba.center.user.user;

import com.tiba.center.exception.InvalidActionException;
import com.tiba.center.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User getUserById(Long userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));
  }

  @Override
  public void changePasswordAtFirstLogin(Long userConnectedId, String newPassword) {
    User user = getUserById(userConnectedId);
    user.setPassword(passwordEncoder.encode(newPassword));
    user.setPasswordChangeRequired(false);
    userRepository.save(user);
  }

  @Override
  public boolean isMustChangePasswordFirstLogin(Long userConnectedId) {
    User user =
        userRepository
            .findById(userConnectedId)
            .orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

    return user.isPasswordChangeRequired();
  }

  @Override
  public void adminChangeUserPassword(AdminChangePasswordRequest request) {
    User user = getUserById(request.idUser());

    if (!request.newPassword().equals(request.confirmPassword())) {
      throw new InvalidActionException("PASSWORDS_DO_NOT_MATCH");
    }

    user.setPassword(passwordEncoder.encode(request.newPassword()));

    if (request.resetPasswordChange()) {
      user.setPasswordChangeRequired(true);
    }

    userRepository.save(user);
  }
}
