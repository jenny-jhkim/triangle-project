package org.comit.project.provider.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "test")
public class TestProperties {

    private String backend;
    private String frontend;
    private String browser;
    private boolean headless; 
    private int timeout;

    // Getters & Setters

    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }

    public String getFrontend() {
        return frontend;
    }

    public void setFrontend(String frontend) {
        this.frontend = frontend;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
