package br.com.trackwayapp.dto.response;

import br.com.trackwayapp.enuns.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultPageableResponseDto<T> {

    protected List<T> content = new ArrayList<>();
    protected String message;
    protected PageableDto pageable;
    protected StatusEnum status = StatusEnum.SUCCESS;

    @JsonIgnore
    public void refreshPageable() {
        if(content != null) {
            pageable = new PageableDto();
            pageable.setTotalElements(this.content.size());
        }
    }
}