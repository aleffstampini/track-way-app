package br.com.trackwayapp.dto.response;

import br.com.trackwayapp.dto.FreightCalculationDto;

import java.util.ArrayList;
import java.util.Collections;

public class FreightCalculationResponseDto extends DefaultPageableResponseDto<FreightCalculationDto> {

    public FreightCalculationResponseDto(FreightCalculationDto freightCalculation) {
        this.content = Collections.singletonList(freightCalculation);
        this.refreshPageable();
    }

    public FreightCalculationResponseDto() {
        this.content = new ArrayList<>();
        this.refreshPageable();
    }

}