package br.com.conrado.fcontrol.domain.hibernate;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.UserBase;

public class UserHibernate extends UserBase implements User {

    @Override
    public String getEmail() {
	return email;
    }

    @Override
    public void setEmail(String email) {
	this.email = email;
    }

}
