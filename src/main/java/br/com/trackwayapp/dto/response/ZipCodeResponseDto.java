package br.com.trackwayapp.dto.response;

import java.util.ArrayList;
import java.util.Collections;

public class ZipCodeResponseDto extends DefaultPageableResponseDto<ZipCodeDetailsResponseDto> {

    public ZipCodeResponseDto(ZipCodeDetailsResponseDto apiResponseDto) {
        this.content = Collections.singletonList(apiResponseDto);
        this.refreshPageable();
    }

    public ZipCodeResponseDto() {
        this.content = new ArrayList<>();
        this.refreshPageable();
    }

}