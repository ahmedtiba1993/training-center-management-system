package com.tiba.center.user.role;

import java.util.List;
import java.util.Optional;

import com.tiba.center.common.PageResponseDto;
import com.tiba.center.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  public Long save(RoleRequest request) {

    Optional<Role> role = roleRepository.findByName(request.name());
    if (role.isPresent()) {
      throw new DuplicateResourceException("ROLE_ALREADY_EXISTS");
    }
    Role roleSaved = roleMapper.toRole(request);
    return roleRepository.save(roleSaved).getId();
  }

  @Transactional(readOnly = true)
  public PageResponseDto<RoleResponse> findAllPaginated(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
    Page<Role> roles = roleRepository.findAll(pageable);
    List<RoleResponse> roleResponses = roles.stream().map(roleMapper::toRoleResponse).toList();
    return PageResponseDto.fromPage(roles, roleResponses);
  }
}
