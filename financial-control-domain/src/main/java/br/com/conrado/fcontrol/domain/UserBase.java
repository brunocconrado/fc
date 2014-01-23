package br.com.conrado.fcontrol.domain;

import java.io.Serializable;
import java.util.Date;

public class UserBase implements Serializable {
    
    protected String name;
    
    protected String email;

    protected String password;
    
    protected Date registrydate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrydate() {
        return registrydate;
    }

    public void setRegistrydate(Date registrydate) {
        this.registrydate = registrydate;
    }
    
}
