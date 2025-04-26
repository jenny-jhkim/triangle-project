package org.comit.project.provider;

import java.util.Properties;

import org.comit.project.provider.properties.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(TestProperties.class)
public class PropertiesProvider {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private TestProperties testProperties;

    @PostConstruct
    public void loadCustomYaml() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("config.yml"));
        Properties props = yaml.getObject();


        if (props != null) {
            this.environment.getPropertySources().addFirst(new PropertiesPropertySource("customConfig", props));

            
            // Manually bind to TestProperties
            org.springframework.boot.context.properties.bind.Binder.get(this.environment)
                .bind("test", TestProperties.class)
                .ifBound(bound -> {
                   
                    testProperties.setBackend(bound.getBackend());
                    testProperties.setFrontend(bound.getFrontend());
                    System.out.println("loadCustomYaml: " + testProperties.getFrontend());
                    testProperties.setBrowser(bound.getBrowser());
                    testProperties.setHeadless(bound.isHeadless());
                    testProperties.setTimeout(bound.getTimeout());
                });
        }
    }

    @PostConstruct
    public void logProperties() {
        System.out.println("Browser loaded from Environment: " + this.environment.getProperty("test.browser"));
        System.out.println("Browser loaded from Bean: " + testProperties.getBrowser());
    }
}

