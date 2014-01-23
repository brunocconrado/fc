package br.com.conrado.fcontrol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.integration.repository.UserRepository;
import br.com.conrado.fcontrol.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(String id) {
	return null;
    }

    @Override
    public void create(User user) {

    }

}
