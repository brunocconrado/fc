package br.com.conrado.fcontrol.web;

import br.com.conrado.fcontrol.domain.User;

public interface UserControl {

    //USER LOGGED VALUE
    static final String USER_ID = "user.id";
    
    public void store(User user);
    
    public User restore();
    
}
