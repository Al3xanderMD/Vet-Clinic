package com.example.MyVet.User.users.role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(Role.ERole name);
    Boolean existsByName(Role.ERole name);
}
