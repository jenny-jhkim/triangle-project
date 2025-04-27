package org.comit.project.triangle.provider;

import java.util.Arrays;
import java.util.Properties;

import org.comit.project.triangle.provider.properties.H2ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(H2ServerProperties.class)
public class PropertiesProvider {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private H2ServerProperties h2ServerProperties;

    @PostConstruct
    public void loadCustomYaml() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("config.yml"));
        Properties props = yaml.getObject();

        if (props != null) {
            this.environment.getPropertySources()
                .addFirst(new PropertiesPropertySource("customConfig", props));

            // Manual binding of h2.server.params
            Binder.get(this.environment)
                .bind("h2.server", H2ServerProperties.class)
                .ifBound(bound -> this.h2ServerProperties.setParams(bound.getParams()));
        }
    }
    
    @PostConstruct
    public void logParams() {    	
    	this.logger.debug("H2 Params: {}", Arrays.toString(this.h2ServerProperties.getParams()));
    }
}
