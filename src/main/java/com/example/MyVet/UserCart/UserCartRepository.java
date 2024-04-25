package com.example.MyVet.UserCart;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends MongoRepository<UserCart, String> {
}
