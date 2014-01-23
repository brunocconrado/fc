package br.com.conrado.fcontrol.service;

import br.com.conrado.fcontrol.domain.User;

public interface UserService {

    public User findUserById(String id);

    public void create(User user);
    
}
