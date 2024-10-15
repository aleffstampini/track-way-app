package br.com.trackwayapp.dto.response;

import java.util.ArrayList;
import java.util.Collections;

public class PostalCodeResponseDto extends DefaultPageableResponseDto<PostalCodeDetailsResponseDto> {

    public PostalCodeResponseDto(PostalCodeDetailsResponseDto apiResponseDto) {
        this.content = Collections.singletonList(apiResponseDto);
        this.refreshPageable();
    }

    public PostalCodeResponseDto() {
        this.content = new ArrayList<>();
        this.refreshPageable();
    }

}