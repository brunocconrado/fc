package br.com.conrado.fcontrol.service;

import br.com.conrado.fcontrol.domain.Credentials;
import br.com.conrado.fcontrol.domain.User;

public interface AuthenticationService {

    
    public User authenticate(Credentials credentials);
}
