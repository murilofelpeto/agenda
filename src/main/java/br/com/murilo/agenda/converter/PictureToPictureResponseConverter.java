package br.com.murilo.agenda.converter;



import br.com.murilo.agenda.dto.response.PictureResponse;
import br.com.murilo.agenda.entity.Picture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PictureToPictureResponseConverter implements Converter<Picture, PictureResponse> {

    @Override
    public PictureResponse convert(final Picture picture) {
        return new PictureResponse(picture.getFileName(), picture.getDownloadUri());
    }
}
