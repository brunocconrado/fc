package br.com.conrado.fcontrol.integration.db.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.conrado.fcontrol.integration.IntegrationProfiles;

@Configuration
@Profile(IntegrationProfiles.DB)
@ComponentScan(basePackages = { "br.com.conrado.fcontrol.integration" })
public class IntegrationDBConfiguration {
    
    @PostConstruct
    public void init() {
	
    }

}
