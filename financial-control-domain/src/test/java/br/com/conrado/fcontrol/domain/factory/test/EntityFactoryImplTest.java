package br.com.conrado.fcontrol.domain.factory.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import br.com.conrado.fcontrol.domain.User;
import br.com.conrado.fcontrol.domain.UserBase;
import br.com.conrado.fcontrol.domain.factory.EntityFactory;
import br.com.conrado.fcontrol.domain.factory.impl.EntityFactoryImpl;
import br.com.conrado.fcontrol.domain.hibernate.UserHibernate;
import br.com.conrado.fcontrol.domain.mongo.UserMongo;

@RunWith(Parameterized.class)
public class EntityFactoryImplTest {
    
    private Class<User> user;
    
    public EntityFactoryImplTest(Class<User> user) {
	this.user = user;
    }
    
    @Parameters
    public static Collection<Object[]> data() {
      Object[][] data = new Object[][] { {UserMongo.class}, {UserHibernate.class} };
      return Arrays.asList(data);
    }

    @Test
    public void getNewInstance() throws ClassNotFoundException {
	
	assertNotNull(user);
	
	EntityFactory factory = new EntityFactoryImpl(user.getPackage());
	assertNotNull(factory);
	
	factory.init();
	
	User instance = factory.getNewInstance(User.class);
	assertNotNull(instance);
	assertEquals(user, instance.getClass());
	assertNull(instance.getEmail());
    }
    
    @Test(expected=ClassNotFoundException.class)
    public void getNewInstanceNotFound() throws ClassNotFoundException {
	
	EntityFactory factory = new EntityFactoryImpl(user.getPackage());
	assertNotNull(factory);
	
	factory.init();
	
	factory.getNewInstance(UserBase.class);
	
    }
}
