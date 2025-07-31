package com.tiba.center.user.role;

import com.tiba.center.common.PageResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {

  public final RoleService RoleService;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Long> saveRole(@RequestBody @Valid RoleRequest request) {
    return ResponseEntity.ok().body(RoleService.save(request));
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping
  public ResponseEntity<PageResponseDto<RoleResponse>> findAllRoles(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
    return ResponseEntity.ok().body(RoleService.findAllPaginated(page, size));
  }
}
