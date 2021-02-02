package com.we.cvr.services.cvbuilder;

import com.we.cvr.models.auth.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
