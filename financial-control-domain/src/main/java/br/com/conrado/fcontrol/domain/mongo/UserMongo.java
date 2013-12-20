package br.com.conrado.fcontrol.domain.mongo;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.UserBase;

public class UserMongo extends UserBase implements User {

    @Override
    public String getLogin() {
	return login;
    }

    @Override
    public String setLogin(String login) {
	return login;
    }

}
