package br.com.trackwayapp.dto.response;

import java.util.ArrayList;

public class StringDefaultPageableResponseDto extends DefaultPageableResponseDto<String> {

    public StringDefaultPageableResponseDto(String message) {
        this.content = new ArrayList<>();
        this.message = message;
        this.refreshPageable();
    }

    public StringDefaultPageableResponseDto() {
        this.content = new ArrayList<>();
        this.refreshPageable();
    }
}