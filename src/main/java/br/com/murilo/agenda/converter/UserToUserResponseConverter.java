package br.com.murilo.agenda.converter;

import br.com.murilo.agenda.dto.response.PictureResponse;
import br.com.murilo.agenda.dto.response.UserResponse;
import br.com.murilo.agenda.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserToUserResponseConverter implements Converter<ApplicationUser, UserResponse> {

    private final ConversionService conversionService;

    @Autowired
    public UserToUserResponseConverter(final ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public UserResponse convert(final ApplicationUser user) {
        PictureResponse picture = this.conversionService.convert(user.getProfilePicture(), PictureResponse.class);
        return new UserResponse(user.getId(), user.getName(), user.getUsername(), picture);
    }
}
