package br.com.conrado.fcontrol.integration.inmemory.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.conrado.fcontrol.integration.IntegrationProfiles;

@Configuration
@Profile(IntegrationProfiles.INMEMORY)
@ComponentScan(basePackages = { "br.com.conrado.fcontrol.integration" })
public class IntegrationInMemoryConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(IntegrationInMemoryConfiguration.class);

    @PostConstruct
    public void init() {
	LOG.info("Init {}", this.getClass().getCanonicalName());
    }

}
