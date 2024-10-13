package br.com.trackwayapp.dto.response;

import lombok.Data;

@Data
public class PageableDto {
    private int pageSize = 0;
    private int pageNumber = 0;
    private int totalPages = 0;
    private int totalElements = 0;
    private boolean sorted = false;
    private boolean paged = false;
    private boolean unpaged = false;
}