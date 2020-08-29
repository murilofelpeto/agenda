package br.com.murilo.agenda.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.murilo.agenda.configuration.FileStorageConfiguration;
import br.com.murilo.agenda.exception.FileNotFoundException;
import br.com.murilo.agenda.exception.FileStorageException;

@Service
public class UploadFilesService {

    public static final String COULD_NOT_CREATE_DIRECTORY = "Could not create the directory where the uploaded files will be stored";
    public static final String INVALID_PATH_SEQUENCE = "File name contains invalid path sequence ";
    public static final String COULD_NOT_CREATE_FILE = "Could not create file ";
    public static final String FILE_NOT_FOUND = "File not found: ";

    private final Path fileStorageLocation;

    @Autowired
    public UploadFilesService(FileStorageConfiguration fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException(COULD_NOT_CREATE_DIRECTORY);
        }
    }

    public String save(final MultipartFile picture) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException(INVALID_PATH_SEQUENCE + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new FileStorageException(COULD_NOT_CREATE_FILE + fileName);
        }
    }

    public Resource getFile(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException(FILE_NOT_FOUND + fileName);
            }

        } catch (Exception e) {
            throw new FileNotFoundException(FILE_NOT_FOUND + fileName);
        }
    }
}
