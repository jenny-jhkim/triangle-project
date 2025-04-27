package org.comit.project.triangle.config.test;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileNamingStrategy implements PhysicalNamingStrategy{
	  private final boolean isTestProfileActive;

	    public ProfileNamingStrategy(Environment environment) {
	        String[] profiles = environment.getActiveProfiles();
	        this.isTestProfileActive = java.util.Arrays.asList(profiles).contains("test");
	    }

	    @Override
	    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
	        if (name == null) return null;

	        String tableName = name.getText();

	        if (isTestProfileActive) {
	            tableName = "TEST_" + tableName.toUpperCase(Locale.ROOT);
	        }

	        return Identifier.toIdentifier(tableName);
	    }

	    @Override public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) { return name; }
	    @Override public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) { return name; }
	    @Override public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) { return name; }
	    @Override public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) { return name; }
}
