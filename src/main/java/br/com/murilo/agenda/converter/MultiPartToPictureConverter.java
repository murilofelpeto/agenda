package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.entity.Picture;
import br.com.murilo.agenda.exception.FileStorageException;
import br.com.murilo.agenda.service.UploadFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class MultiPartToPictureConverter implements Converter<MultipartFile, Picture> {

    public static final String FILE_TOO_BIG = "This file is too big or it's not in the correct type";
    public static final int MAX_KB = 51200;
    private final UploadFilesService uploadFilesService;

    @Autowired
    public MultiPartToPictureConverter(final UploadFilesService uploadFilesService) {
        this.uploadFilesService = uploadFilesService;
    }

    @Override
    public Picture convert(final MultipartFile picture) {
        if(isAcceptedType(picture.getContentType()) && hasCorrectedSize(picture.getSize())) {
            String fileName = uploadFilesService.save(picture);
            String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadFile/").path(fileName).toUriString();
            String fileType = picture.getContentType();
            Long fileSize = picture.getSize();
            return new Picture(fileName, fileDownloadURI, fileType, fileSize);
        }
        throw new FileStorageException(FILE_TOO_BIG);
    }

    private boolean hasCorrectedSize(final Long size) {
        if(size > 0 && size <= MAX_KB) {
            return true;
        }
        return false;
    }

    private boolean isAcceptedType(final String contentType) {
        if(contentType.equals("image/jpg") ||
           contentType.equals("image/png") ||
           contentType.equals("image/jpeg")) {
            return true;
        }
        return false;
    }
}
