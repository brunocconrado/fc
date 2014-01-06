package br.com.conrado.fcontrol.web.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configurable
@EnableWebMvc
@ComponentScan("br.com.conrado.fcontrol")
public class WebConfig {

    public void authenticationManager() {
	
    }
    
}
