package br.com.murilo.agenda.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Paths;

@ConfigurationProperties(prefix = "uploads")
public class FileStorageConfiguration {

    private final String uploadDir = Paths.get("uploads").toString();

    public String getUploadDir() {
        return uploadDir;
    }
}
