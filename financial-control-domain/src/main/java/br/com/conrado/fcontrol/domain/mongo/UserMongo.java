package br.com.conrado.fcontrol.domain.mongo;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.UserBase;

public class UserMongo extends UserBase implements User {

    @Override
    public String getEmail() {
	return email;
    }

    @Override
    public void setEmail(String email) {
	this.email = email;
    }

}
