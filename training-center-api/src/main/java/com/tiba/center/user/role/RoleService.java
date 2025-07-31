package com.tiba.center.user.role;

import com.tiba.center.common.PageResponseDto;

public interface RoleService {

  Long save(RoleRequest request);

  PageResponseDto<RoleResponse> findAllPaginated(int page, int size);
}
