package br.com.conrado.fcontrol.domain.mongo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import br.com.conrado.fcontrol.domain.factory.EntityFactory;
import br.com.conrado.fcontrol.domain.mongo.UserMongo;
import br.com.conrado.fcontrol.domain.profile.DomainProfiles;


@Configurable
@ComponentScan(basePackages = "br.com.conrado.fcontrol.domain")
@Profile(DomainProfiles.HIBERNATE)
public class DomainMongoConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public EntityFactory entityfactory() {
	return new EntityFactory(UserMongo.class.getPackage());
    }
    
}
