package dev.yuizho.springhtmlx.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "backend")
public record BackendProperties(String hostName, int port) {
}
