package br.com.murilo.agenda.dto.response;

public class PictureResponse {

    private String fileName;
    private String downloadUri;

    public PictureResponse(final String fileName, final String downloadUri) {
        this.fileName = fileName;
        this.downloadUri = downloadUri;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownloadUri() {
        return downloadUri;
    }
}
