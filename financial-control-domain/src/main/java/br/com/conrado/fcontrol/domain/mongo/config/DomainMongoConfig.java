package br.com.conrado.fcontrol.domain.mongo.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import br.com.conrado.fcontrol.domain.factory.EntityFactory;
import br.com.conrado.fcontrol.domain.factory.impl.EntityFactoryImpl;
import br.com.conrado.fcontrol.domain.mongo.UserMongo;
import br.com.conrado.fcontrol.domain.profile.DomainProfiles;


@Configurable
@ComponentScan(basePackages = "br.com.conrado.fcontrol.domain")
@Profile(DomainProfiles.HIBERNATE)
public class DomainMongoConfig {
    
private static final Logger LOG = LoggerFactory.getLogger(DomainMongoConfig.class);
    
    @PostConstruct
    public void init() {
	LOG.info("Init {}", this.getClass().getCanonicalName());
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public EntityFactory entityfactory() {
	return new EntityFactoryImpl(UserMongo.class.getPackage());
    }
    
}
