package br.com.conrado.fcontrol.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.conrado.fcontrol.web.UserControl;
import br.com.conrado.fcontrol.web.WebProfile;
import br.com.conrado.fcontrol.web.impl.UserSessionControl;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.conrado.fcontrol")
@Profile(WebProfile.SESSION)
public class WebSessionConfig extends WebConfig {

    @Override
    public UserControl userControl() {
	return new UserSessionControl();
    }

}
