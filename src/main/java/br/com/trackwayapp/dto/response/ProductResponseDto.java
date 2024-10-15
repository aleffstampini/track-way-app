package br.com.trackwayapp.dto.response;

import br.com.trackwayapp.domain.Product;
import org.springframework.data.domain.Page;

public class ProductResponseDto extends DefaultPageableResponseDto<Product> {

    public ProductResponseDto(Page<Product> products) {
        this.content = products.getContent();
        this.refreshPageable();

        this.getPageable().setPageSize(products.getSize());
        this.getPageable().setPageNumber(products.getNumber());
        this.getPageable().setTotalPages(products.getTotalPages());
        this.getPageable().setTotalElements((int) products.getTotalElements());
        this.getPageable().setSorted(false);
        this.getPageable().setPaged(true);
        this.getPageable().setUnpaged(false);
    }
}