package org.uncertweb.aquacrop.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestEnvironment {
	
	private Properties properties;
	private static TestEnvironment environment;

	private TestEnvironment() {
		properties = new Properties();
		InputStream is = null;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream("aquacrop.properties");
			properties.load(is);
		}
		catch (NullPointerException e) {
			System.err.println("Couldn't find aquacrop.properties, tests will fail.");
		}
		catch (IOException e) {
			System.err.println("Couldn't load aquacrop.properties, tests will fail.");
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) { }
		}
	}
	
	public static TestEnvironment getInstance() {
		if (environment == null) {
			environment = new TestEnvironment();
		}
		return environment;
	}
	
	public int getPort() {
		return Integer.parseInt(properties.getProperty("port"));
	}
	
	public String getBasePath() {
		return processStringValue(properties.getProperty("basePath"));
	}
	
	public String getBasePathOverride() {
		return processStringValue(properties.getProperty("basePathOverride")); 
	}
	
	public String getPrefixCommand() {
		return processStringValue(properties.getProperty("prefixCommand"));
	}
	
	private String processStringValue(String value) {
		if (value.trim().length() == 0) {
			return null;
		}
		return value;
	}

}
