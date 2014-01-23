package br.com.conrado.fcontrol.domain;

import java.util.Date;

public interface User {

    String getEmail();
    
    void setEmail(String email);
    
    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    Date getRegistrydate();

    void setRegistrydate(Date registrydate);
    
}
