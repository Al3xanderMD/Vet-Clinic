package com.example.MyVet.User.users.role;

import com.example.MyVet.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional
    public Role getRoleByName(Role.ERole name) throws EntityNotFoundException {
        Role role =
                roleRepository
                        .findByName(name)
                        .orElseThrow(() -> new EntityNotFoundException("Role Not Found: " + name));
        return role;
    }

    @Transactional
    public Boolean existsRoleByName(Role.ERole name) {
        return roleRepository.existsByName(name);
    }
}
