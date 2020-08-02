package br.com.murilo.agenda.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties(prefix = "uploads")
public class FileStorageConfiguration {

    private String uploadDir = Paths.get("uploads").toString();

    public String getUploadDir() {
        return uploadDir;
    }
}
