package br.com.conrado.fcontrol.domain.hibernate;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.UserBase;

public class UserHibernate extends UserBase implements User {

    @Override
    public String getLogin() {
	return login;
    }

    @Override
    public String setLogin(String login) {
	return this.login = login;
    }

}
