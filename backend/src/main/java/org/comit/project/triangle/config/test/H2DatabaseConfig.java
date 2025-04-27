package org.comit.project.triangle.config.test;

import java.sql.SQLException;

import org.comit.project.triangle.provider.properties.H2ServerProperties;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class H2DatabaseConfig {
	@Autowired
	H2ServerProperties properties;
	
	@Bean(initMethod = "start", destroyMethod = "stop")   
	Server inMemoryH2DatabaseServer() throws SQLException {

	    return Server.createTcpServer(this.properties.getParams());
	}
}

