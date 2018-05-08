package com.nub.userService.service.impl;

import com.nub.userService.entity.Token;
import com.nub.userService.repository.TokenRepository;
import com.nub.userService.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenService implements CrudService<Token, String> {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token createOrUpdate(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Token find(String id) { return tokenRepository.findById(id).get(); }

    @Override
    public Iterable<Token> findAll() { return tokenRepository.findAll(); }

    @Override
    public boolean delete(String id) {
        tokenRepository.deleteById(id);
        return true;
    }
}
