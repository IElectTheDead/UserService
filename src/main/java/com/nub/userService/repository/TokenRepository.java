package com.nub.userService.repository;

import com.nub.userService.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
}
