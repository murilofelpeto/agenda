package br.com.murilo.agenda.entity;

public class Picture {

    private String fileName;
    private String downloadUri;
    private String fileType;
    private Long fileSize;

    public Picture(final String fileName,
                   final String downloadUri,
                   final String fileType,
                   final Long fileSize) {

        this.fileName = fileName;
        this.downloadUri = downloadUri;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(final String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(final String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(final Long fileSize) {
        this.fileSize = fileSize;
    }
}
