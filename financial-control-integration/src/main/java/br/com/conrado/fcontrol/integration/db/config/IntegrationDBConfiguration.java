package br.com.conrado.fcontrol.integration.db.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.conrado.fcontrol.integration.IntegrationProfiles;

@Configuration
@Profile(IntegrationProfiles.DB)
@ComponentScan(basePackages = { "br.com.conrado.fcontrol.integration" })
public class IntegrationDBConfiguration {
    
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationDBConfiguration.class);

    @PostConstruct
    public void init() {
	LOG.info("Init {}", this.getClass().getCanonicalName());
    }

}
